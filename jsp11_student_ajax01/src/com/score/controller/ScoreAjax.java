package com.score.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/score.cal")
public class ScoreAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		
		int sum = kor + eng + math;
		double avg = sum/3.0;
		
		// JSONObject : json-simple jar 파일에 있던 class
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("sum", sum);
		obj.put("avg", avg);
		String res = obj.toJSONString();	// json 오브젝트를 string 결과로 만들기
		System.out.println("Servlet에서 만들어진 결과 : " + res);
		
		PrintWriter out = response.getWriter();	// 서버에서 클라이언트로 응답을 내보내기 위해 출력스트림을 얻고
		out.println(res);	// 그 출력스트림에 결과를 기록
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		doGet(request, response);
	}

}
