<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
</head>
<body>

	<h1>MVCBOARD :: 글 작성</h1>
	
	<form action="MVCController" method="post">
		<input type="hidden" name="command" value="boardWrite">
		<table border="1">
			<tr>
				<th>이름</th>
				<td><input type="text" name="WRITER"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="TITLE"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="CONTENT"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="글쓰기">
					<input type="button" value="취소" onclick="location.href='MVCController?command=boardList'">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>