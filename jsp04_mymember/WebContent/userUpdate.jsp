<%@page import="com.my.BIZ.MyMemberBizImpl"%>
<%@page import="com.my.BIZ.MyMemberBiz"%>
<%@page import="com.my.DTO.MyMemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보 수정</title>
</head>

<%
	MyMemberDTO dto = (MyMemberDTO) request.getAttribute("myInfo");
%>

<body>

	<h1>MYMEMBER :: 정보 수정</h1>
	
	<form action="loginController.jsp" method="post">
		<input type="hidden" name="command" value="updateMyInfoRes">
		<input type="hidden" name="MYNO" value="<%= dto.getMyNo() %>">
		<table border="1">
			<col width="100">
			<col width="100">
			<tr>
				<th>아이디</th>
				<th><%= dto.getId() %></th>
			</tr>
			<tr>
				<th>비밀번호</th>
				<th><input type="password" name="PW" value="<%= dto.getPw() %>"></th>
			</tr>
			<tr>
				<th>이름</th>
				<th><input type="text" name="NAME" value="<%= dto.getName() %>"></th>
			</tr>
			<tr>
				<th>주소</th>
				<th><input type="text" name="ADDR" value="<%= dto.getAddr() %>"></th>
			</tr>
			<tr>
				<th>전화번호</th>
				<th><input type="text" name="PHONE" value="<%= dto.getPhone() %>"></th>
			</tr>
			<tr>
				<th>이메일</th>
				<th><input type="text" name="EMAIL" value="<%= dto.getEmail() %>"></th>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정">
					<input type="button" value="취소" onclick="location.href='loginController.jsp?command=myInfo&MYNO=<%= dto.getMyNo() %>'">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>