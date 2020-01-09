<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
</head>
<body>

	<form action="loginController.jsp" method="post">
		<input type="hidden" name="command" value="login">
		<table border="1">
			<col width="100">
			<col width="100">
			<tr>
				<th>ID</th>
				<th><input type="text" name="ID"></th>
			</tr>
			<tr>
				<th>PW</th>
				<th><input type="password" name="PW"></th>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="LOGIN">
					<input type="button" value="JOIN US" onclick="location.href='loginController.jsp?command=join'">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>