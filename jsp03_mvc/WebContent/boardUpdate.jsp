<%@page import="com.mvc.DTO.MVCBoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>

<%
	MVCBoardDTO dto = (MVCBoardDTO)request.getAttribute("boardContent");
%>

<body>

	<h1>MVCBOARD :: UPDATE</h1>
	
	<form action="MVCController.jsp" method="post">
		<input type="hidden" name="command" value="updateRes">
		<input type="hidden" name="SEQ" value="<%= dto.getSeq() %>">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="WRITER" value="<%= dto.getWriter() %>"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="TITLE" value="<%= dto.getTitle() %>"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="CONTENT"><%= dto.getContent() %></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정">
					<input type="button" value="취소" onclick="location.href='MVCController.jsp?command=content&SEQ=<%= dto.getSeq() %>'">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>