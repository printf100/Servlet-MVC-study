<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>

<body>

	<h1>ANSWERBOARD :: 글 수정</h1>
	
	<form action="myupdateRes" method="post">
		<input type="hidden" name="BOARDNO" value="${dto.boardNo }">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="WRITER" value="${dto.writer }"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="TITLE" value="${dto.title }"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="CONTENT">${dto.content }</textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정">
					<input type="button" value="취소" onclick="location.href='mydetail?BOARDNO=${dto.boardNo }'">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>