<%
	// 브라우저가 캐시에 화면(응돱된 도큐먼트)을 저장하지 않도록 설정 (로그아웃하면 로그아웃한 상태가 되도록)
	response.setHeader("Pragma", "no-cache");	// http 1.0
	response.setHeader("Cache-control", "no-store");	// http 1.1
	response.setHeader("Expires", "0");	// proxy server
%>

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

	if(dto == null) {	// 로그아웃된 상태라면
		pageContext.forward("index.jsp");
	}
%>

<body>

	<h1><%= dto.getName() %>님 환영합니다! <a href="loginController.jsp?command=logout">LOGOUT</a></h1>
	<h3>등급 : <%= dto.getMyRole() %></h3>
	
	<div>
		<div>
			<a href="myController.jsp?command=selectList">회원정보 조회(ALL)</a>
		</div>
		<div>
			<a href="myController.jsp?command=selectEnabled">회원정보 조회(ENABLED)</a>
		</div>
	</div>

</body>
</html>