package com.hello.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public HelloServlet() {
    	System.out.println("Servlet 생성자!");
    }

    private String initParam;
    private String contextParam;
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Servlet init!");
		
		contextParam = config.getServletContext().getInitParameter("jdbcurl");
		initParam = config.getInitParameter("driver");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("GET 방식으로 넘어옴!");
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("POST 방식으로 넘어옴!");
		System.out.println("initParam : " + initParam);
		/*
		 * initParam이 null인 이유 ?
		 	HelloServlet 클래스가 hello라는 이름으로 서블릿 객체 생성이 되는데,

			hello.do라고 요청이 들어오면
			hello라는 이름으로 생성된 서블릿 객체에 연결해주겠다는 뜻이다.
			
			context-param은 프로젝트 전체에서 사용할 수 있지만,
			init param은 생성된 servlet 객체 안에서만 사용할 수 있는 변수임
			
			form태그를 통해 액션을 HelloServlet이라고 지정하여 post방식으로 넘어갈 때
			web.xml과 서블릿의 어노테이션이 충돌해서
			web.xml에서 설정한 initparam을 인식하지 못한다.
		 */
		System.out.println("contextParam : " + contextParam);
		
		String command = request.getParameter("command");
		System.out.println("command : " + command);
		
		PrintWriter out = response.getWriter();
		out.println("<h1 style='background-color:skyblue'>HelloServlet</h1>");
		out.println("<h3>init-service-doGet/doPost-destroy</h3>");
		out.println("<a href='home.html'>돌아가기</a>");
	}
	
	@Override
	public void destroy() {
		System.out.println("Servlet destroy!");
	}

	
}
