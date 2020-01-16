<%@page import="com.mvc.DTO.MVCBoardDTO"%>
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

	<jsp:useBean id="boardContent" class="com.mvc.DTO.MVCBoardDTO" scope="request"></jsp:useBean>

	<h1>MVCBOARD :: 글 수정</h1>
	
	<form action="con.do" method="post">
		<input type="hidden" name="command" value="boardUpdate">
		<input type="hidden" name="SEQ" value="<jsp:getProperty property="seq" name="boardContent"/>">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="WRITER" value="<jsp:getProperty property="writer" name="boardContent"/>"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="TITLE" value="<jsp:getProperty property="title" name="boardContent"/>"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="CONTENT"><jsp:getProperty property="content" name="boardContent"/></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정">
					<input type="button" value="취소" onclick="location.href='con.do?command=boardContent&SEQ=<jsp:getProperty property="seq" name="boardContent"/>'">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>