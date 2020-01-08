<%@page import="com.md.DAO.MDBoardDAOImpl"%>
<%@page import="com.md.DAO.MDBoardDAO"%>
<%@page import="com.md.DTO.MDBoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
	int seq = Integer.parseInt(request.getParameter("SEQ"));

	MDBoardDAO dao = new MDBoardDAOImpl();
	MDBoardDTO dto = dao.selectOne(seq);
%>

<body>

	<%@ include file="./form/header.jsp" %>

	<h1>MDBOARD : SELECT_ONE</h1>

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
				<input type="button" value="수정" onclick="location.href='boardUpdate.jsp?SEQ=<%= dto.getSeq() %>'">
				<input type="button" value="삭제" onclick="location.href='boardDeleteRes.jsp?SEQ=<%= dto.getSeq() %>'">
				<input type="button" value="목록" onclick="location.href='boardList.jsp'">
			</td>
		</tr>
	</table>
	
	<%@ include file="./form/footer.jsp" %>

</body>
</html>