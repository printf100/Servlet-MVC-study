package com.bike.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bike.DTO.BikeDTO;
import com.bike.biz.BikeBiz;
import com.bike.biz.BikeBizImpl;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jdk.nashorn.internal.parser.JSONParser;

/**
 * Servlet implementation class BikeServlet
 */
@WebServlet("/BikeServlet")
public class BikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		BikeBiz biz = new BikeBizImpl();
		biz.delete();
		
		if(command.equals("first")) {
			response.sendRedirect("bike01.jsp");
		}
		
		else if(command.equals("firstdb")) {
			String[] bike = request.getParameterValues("bike");
			
			List<BikeDTO> bikes = new ArrayList<BikeDTO>();
			for(int i=0; i<bike.length; i++) {
				String[] tmp = bike[i].split("/");
				System.out.println(tmp[0] + "/t" + tmp[1] + "/t" + tmp[2] + "/t" + tmp[3] + "/t" + tmp[4] + "/t" + tmp[5] + "/t" + tmp[6]);
	
				BikeDTO dto = new BikeDTO();
				dto.setADDR_GU(tmp[0]);
				dto.setCONTENT_ID(Integer.parseInt(tmp[1]));
				dto.setCONTENT_NAME(tmp[2]);
				dto.setNEW_ADDR(tmp[3]);
				dto.setCRADLE_COUNT(Integer.parseInt(tmp[4]));
				dto.setLONGITUDE(Double.parseDouble(tmp[5]));
				dto.setLATITUDE(Double.parseDouble(tmp[6]));
				
				bikes.add(dto);
			}
			
			int res = biz.insert(bikes);
			
			if(res > 0) {
				System.out.println("DB 저장 성공!");
				response.sendRedirect("index.html");
			} else {
				System.out.println("DB 저장 실패");
				RequestDispatcher dispatcher = request.getRequestDispatcher("bike01.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		else if(command.equals("second")) {
			response.sendRedirect("bike02.jsp");
		}
		
		else if(command.equals("seconddb")) {
			
			String txt = request.getParameter("obj");
			//System.out.println(txt);
			
			JsonElement element = JsonParser.parseString(txt);
			//System.out.println(element.getAsJsonObject().get("DESCRIPTION"));
			
			List<BikeDTO> list = new ArrayList<BikeDTO>();
			
			for(int i=0; i<element.getAsJsonObject().get("DATA").getAsJsonArray().size(); i++) {
				JsonObject tmp = element.getAsJsonObject().get("DATA").getAsJsonArray().get(i).getAsJsonObject();
				
				//System.out.println(tmp.get("addr_gu").getAsString());
				JsonElement addr_gu_je = tmp.get("addr_gu");
				JsonElement content_id_je = tmp.get("content_id");
				JsonElement content_nm_je = tmp.get("content_nm");
				JsonElement new_addr_je = tmp.get("new_addr");
				JsonElement cradle_count_je = tmp.get("cradle_count");
				JsonElement longitude_je = tmp.get("longitude");
				JsonElement latitude_je = tmp.get("latitude");
				
				String addr_gu = addr_gu_je.getAsString();
				int content_id = content_id_je.getAsInt();
				String content_nm = content_nm_je.getAsString();
				String new_addr = new_addr_je.getAsString();
				int cradle_count = cradle_count_je.getAsInt();
				double longitude = longitude_je.getAsDouble();
				double latitude = latitude_je.getAsDouble();
				
				BikeDTO dto = new BikeDTO(addr_gu, content_id, content_nm, new_addr, cradle_count, longitude, latitude);
				list.add(dto);
			}
			
			int res = biz.insert(list);
			
			if(res == 1163) {
				System.out.println("insert 성공");
			} else {
				System.out.println("insert 실패");
			}
			
			response.getWriter().append(res+"");
		}
	}

}
