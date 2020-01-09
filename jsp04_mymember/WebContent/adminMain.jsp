<%@page import="com.my.DTO.MyMemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 메인</title>
</head>

<%
	MyMemberDTO dto = (MyMemberDTO) session.getAttribute("dto");
%>

<body>

	<h1><%= dto.getName() %>님 환영합니다! <a href="">LOGOUT</a></h1>
	<h3>등급 : <%= dto.getMyRole() %></h3>
	
	<div>
		<div>
			<a href="">회원정보 조회(ALL)</a>
		</div>
		<div>
			<a href="">회원정보 조회(ENABLED)</a>
		</div>
	</div>

</body>
</html>