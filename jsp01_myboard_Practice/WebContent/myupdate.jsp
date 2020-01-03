<%@page import="DTO.MyBoardDTO"%>
<%@page import="DAO.MyBoardDAOImpl"%>
<%@page import="DAO.MyBoardDAO"%>
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

	<h1>MYBOARD :: UPDATE</h1>
	
	<form action="myupdateRes.jsp" method="post">
		<input type="hidden" name="MYNO" value="<%= myNo %>">
		<table border="1">
			<tr>
				<th>이름</th>
				<td><input type="text" name="MYNAME" value="<%= dto.getMyName() %>">
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="MYTITLE" value="<%= dto.getMyTitle() %>">
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="6" cols="60" name="MYCONTENT"><%= dto.getMyContent() %></textarea>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="수정">
					<input type="button" value="취소" onclick="location.href='mydetail.jsp?MYNO=<%= dto.getMyNo() %>'">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>