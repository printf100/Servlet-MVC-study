<%@page import="DAO.MyBoardDAOImpl"%>
<%@page import="DAO.MyBoardDAO"%>
<%@page import="DTO.MyBoardDTO"%>
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
<body>

<%
	int myNo = Integer.parseInt(request.getParameter("MYNO"));

	MyBoardDAO dao = new MyBoardDAOImpl();
	MyBoardDTO dto = dao.selectOne(myNo);
%>

	<h1>MYBOARD :: SELECT_ONE</h1>
	
	<table border="1">
		<tr>
			<th>이름</th>
			<td><%= dto.getMyName() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%= dto.getMyTitle() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" readonly="readonly"><%= dto.getMyContent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="location.href='myupdate.jsp?MYNO=<%= dto.getMyNo() %>'">
				<input type="button" value="삭제" onclick="location.href='mydeleteRes.jsp?MYNO=<%= dto.getMyNo() %>'">
				<input type="button" value="목록" onclick="location.href='mylist.jsp'">
			</td>
		</tr>
	</table>

</body>
</html>