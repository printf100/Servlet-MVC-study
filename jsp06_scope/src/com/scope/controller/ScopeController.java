package com.scope.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ScopeController
 */
@WebServlet("/ScopeController")
public class ScopeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ScopeController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("Controller 도착!");
		
		String pageId = (String) request.getAttribute("pageId");
		
		String requestId = (String) request.getAttribute("requestId");	// Object이므로 String으로 명시적 형변환
		
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("sessionId");
		
		ServletContext application = getServletContext();
		String applicationId = (String) application.getAttribute("applicationId");
		
		System.out.println("request : " + requestId);
		System.out.println("session : " + sessionId);
		System.out.println("application : " + applicationId);
		
		String param = request.getParameter("req");
		System.out.println("request parameter : " + param);
		
//		PrintWriter out = response.getWriter();
//		out.println("<h1>응답</h1>");
//		out.println("<table border='1'>");
//		out.println("<tr>");
//		out.println("<th>SCOPE</th><th>값</th>");
//		out.println("</tr>");
//		out.println("<tr>");
//		out.println("<td>page</td>");
//		out.println("<td>" + pageId + "</td>");
//		out.println("</tr>");
//		out.println("<tr>");
//		out.println("<td>request</td>");
//		out.println("<td>" + requestId + "</td>");
//		out.println("</tr>");
//		out.println("<tr>");
//		out.println("<td>session</td>");
//		out.println("<td>" + sessionId + "</td>");
//		out.println("</tr>");
//		out.println("<tr>");
//		out.println("<td>application</td>");
//		out.println("<td>" + applicationId + "</td>");
//		out.println("</tr></table>");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
		request.setAttribute("requestId", "Servlet에서 보내준 request");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		doGet(request, response);
	}

}
