<%@page import="com.md.DTO.MDBoardDTO"%>
<%@page import="com.md.DAO.MDBoardDAOImpl"%>
<%@page import="com.md.DAO.MDBoardDAO"%>
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
	int seq = Integer.parseInt(request.getParameter("SEQ"));

	MDBoardDAO dao = new MDBoardDAOImpl();
	MDBoardDTO dto = dao.selectOne(seq);
%>

<body>

	<%@ include file="./form/header.jsp" %>

	<h1>MDBOARD :: UPDATE</h1>
	
	<form action="boardUpdateRes.jsp?SEQ=<%= seq %>" method="post">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="WRITER" value="<%= dto.getWriter() %>">
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="TITLE" value="<%= dto.getTitle() %>">
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="6" cols="60" name="CONTENT"><%= dto.getContent() %></textarea>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정">
					<input type="button" value="취소">
				</td>
			</tr>
		</table>
	</form>	

	<%@ include file="./form/footer.jsp" %>

</body>
</html>