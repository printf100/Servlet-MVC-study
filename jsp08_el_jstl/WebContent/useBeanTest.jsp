<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>USEBEAN TEST</title>
</head>
<body>

	<jsp:useBean id="lee" class="com.test.DTO.Score" scope="session"></jsp:useBean>
	<!-- Score lee = new Score(); 와 같은 뜻 -->
	
	<jsp:setProperty property="name" name="lee" value="이순신"/>
	<!-- lee.setName("이순신") -->
	
	<jsp:getProperty property="name" name="lee"/>
	<!-- lee.getName(); -->
	
	<a href="res.jsp">RESULT</a>

</body>
</html>