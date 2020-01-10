<%@page import="com.my.DTO.MyMemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보</title>
</head>

<%
	MyMemberDTO dto = (MyMemberDTO) request.getAttribute("myInfo");
%>

<body>

	<h1><%= dto.getName() %>님의 정보</h1>
	
	<table border="1">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>주소</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>등급</th>
		</tr>
		<tr>
			<td><%= dto.getMyNo() %></td>
			<td><%= dto.getId() %></td>
			<td><%= dto.getPw() %></td>
			<td><%= dto.getName() %></td>
			<td><%= dto.getAddr() %></td>
			<td><%= dto.getPhone() %></td>
			<td><%= dto.getEmail() %></td>
			<td><%= dto.getMyRole() %></td>
		</tr>
		<tr>
			<td colspan="9">
				<input type="button" onclick="location.href='loginController.jsp?command=updateMyInfoForm&MYNO=<%= dto.getMyNo() %>'" value="정보수정">
				<input type="button" onclick="location.href='loginController.jsp?command=deleteUser&MYNO=<%= dto.getMyNo() %>'" value="탈퇴">
				<button onclick="location.href='userMain.jsp'">메인으로</button>
			</td>
		</tr>
	</table>

</body>
</html>