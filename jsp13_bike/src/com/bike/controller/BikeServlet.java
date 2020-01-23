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
	}

}
