<%@page import="com.my.BIZ.MyMemberBizImpl"%>
<%@page import="com.my.BIZ.MyMemberBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String command = request.getParameter("command");
	System.out.println("[ " + command + " ]");
	
	MyMemberBiz biz = new MyMemberBizImpl();
	
	if(command.equals("login")) {
		String ID = request.getParameter("ID");
		String PW = request.getParameter("PW");
	}
%>

</body>
</html>