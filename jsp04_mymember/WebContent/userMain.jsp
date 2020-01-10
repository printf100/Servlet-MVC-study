<%@page import="com.my.DTO.MyMemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반회원 메인</title>
</head>

<%
	MyMemberDTO dto = (MyMemberDTO) session.getAttribute("dto");

	if(dto == null) {	// 로그아웃된 상태라면
		pageContext.forward("index.jsp");
	}
%>

<body>

	<h1><%= dto.getName() %>님 환영합니다! <a href="loginController.jsp?command=logout">LOGOUT</a></h1>
	<h3>등급 : <%= dto.getMyRole() %></h3>
	
	<div>
		<div>
			<a href="loginController.jsp?command=myInfo&MYNO=<%= dto.getMyNo() %>">내 정보 조회</a>
		</div>
	</div>

</body>
</html>