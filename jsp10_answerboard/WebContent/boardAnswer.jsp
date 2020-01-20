<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글</title>
</head>
<body>

	<h1>ANSWERBOARD :: 답글 달기</h1>

	<form action="myanswerRes" method="post">
		<input type="hidden" name="BOARDNO" value="${BOARDNO}">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="WRITER"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="TITLE" value="${originalTITLE}"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="CONTENT"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="완료">
					<input type="button" value="취소" onclick="location.href='mylist'">
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>