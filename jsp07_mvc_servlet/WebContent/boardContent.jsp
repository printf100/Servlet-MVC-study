<%@page import="com.mvc.DTO.MVCBoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용</title>
</head>

<%
	MVCBoardDTO dto = (MVCBoardDTO)request.getAttribute("boardContent");
%>

<body>

	<h1>MVCBOARD :: 글 내용</h1>
	
	<table border="1">
		<tr>
			<th>작성자</th>
			<td><%= dto.getWriter() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%= dto.getTitle() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" readonly="readonly"><%= dto.getContent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="location.href='MVCController?command=updateForm&SEQ=<%= dto.getSeq() %>'">
				<input type="button" value="삭제" onclick="location.href='MVCController?command=boardDelete&SEQ=<%= dto.getSeq() %>'">
				<input type="button" value="목록" onclick="location.href='MVCController?command=boardList'">
			</td>
		</tr>
	</table>	

</body>
</html>