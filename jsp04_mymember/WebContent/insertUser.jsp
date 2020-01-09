<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

	<h1>MYMEMBER :: 회원가입</h1>
	
	<form action="loginController.jsp" method="post">
		<input type="hidden" name="command" value="joinRes">
		<table border="1">
			<col width="100">
			<col width="100">
			<tr>
				<th>아이디</th>
				<th><input type="text" name="ID"><input type="button" value="중복확인" onclick=""></th>
			</tr>
			<tr>
				<th>비밀번호</th>
				<th><input type="password" name="PW"></th>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<th><input type="password"></th>
			</tr>
			<tr>
				<th>이름</th>
				<th><input type="text" name="NAME"></th>
			</tr>
			<tr>
				<th>주소</th>
				<th><input type="text" name="ADDR"></th>
			</tr>
			<tr>
				<th>전화번호</th>
				<th><input type="text" name="PHONE"></th>
			</tr>
			<tr>
				<th>이메일</th>
				<th><input type="text" name="EMAIL"></th>
			</tr>
			<tr>
				<th>등급</th>
				<th><input type="radio" name="MYROLE" value="ADMIN"></th>
				<th><input type="radio" name="MYROLE" value="USER"></th>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="JOIN">
					<input type="button" value="CANCEL" onclick="location.href='index.jsp'">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>