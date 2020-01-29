package com.cal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cal.DAO.CalBoardDAO;
import com.cal.DAO.Util;
import com.cal.DTO.CalBoardDTO;

@WebServlet("/calendar.do")
public class CalendarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("command : " + command);
		
		CalBoardDAO dao = new CalBoardDAO();
		
		if(command.equals("calendar")) {
			response.sendRedirect("calendar.jsp");
		}
		
		else if(command.equals("insertcal")) {
			String year = request.getParameter("year");
			String month = Util.isTwo(request.getParameter("month"));
			String date = Util.isTwo(request.getParameter("date"));
			String hour = Util.isTwo(request.getParameter("hour"));
			String min = Util.isTwo(request.getParameter("min"));
			
			String mDate = year + month + date + hour + min;
			
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			CalBoardDTO dto = new CalBoardDTO(id, title, content, mDate);
			
			int res = dao.insert(dto);
			
			if(res > 0) {
				jsResponse("일정 등록 성공!", "calendar.do?command=calendar", response);
			} else {
				request.setAttribute("msg", "일정 등록 실패");
				dispatch("error.jsp", request, response);
			}
		}
		
		else if(command.equals("list")) {
			String year = request.getParameter("year");
			String month = Util.isTwo(request.getParameter("month"));
			String date = Util.isTwo(request.getParameter("date"));
			
			String yyyyMMdd = year + month + date;
			
			List<CalBoardDTO> list = dao.selectList("kh", yyyyMMdd);
			
			request.setAttribute("list", list);
			dispatch("calendarlist.jsp", request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		doGet(request, response);
	}
	
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('" + msg + "')");
		out.println("location.href='" + url + "'");
		out.println("</script>");
	}

}
