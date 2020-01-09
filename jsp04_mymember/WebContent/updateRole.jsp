<%@page import="com.my.DTO.MyMemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등급변경</title>
</head>

<%
	MyMemberDTO dto = (MyMemberDTO) request.getAttribute("selectOneUser");
%>

	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">
	
		$(function() {
			$("select option").each(function() {
				if($(this).val() == "<%= dto.getMyRole() %>") {
					$(this).prop("selected", "selected");
				}
			})
		})
	
	</script>

<body>

	<h1>MYMEMBER :: 회원 등급 변경</h1>
	
	<form action="loginController.jsp" method="post">
		<input type="hidden" name="command" value="updateRoleRes">
		<input type="hidden" name="MYNO" value="<%= dto.getMyNo() %>">
		
		<table border="1">
			<tr>
				<th>번호</th>
				<td><%= dto.getMyNo() %></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><%= dto.getName() %></td>
			</tr>
			<tr>
				<th>등급</th>
				<td>
					<select name="MYROLE">
						<option value="USER">일반회원
						<option value="MANAGER">우수회원
						<option value="ADMIN">관리자
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="변경">
					<input type="button" value="취소" onclick="location.href='loginController.jsp?command=selectEnabled'">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>