<%@page import="DAO.MyBoardDAOImpl"%>
<%@page import="DAO.MyBoardDAO"%>
<%@page import="DTO.MyBoardDTO"%>
<%@page import="java.util.List"%>
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
	MyBoardDAO dao = new MyBoardDAOImpl();
	List<MyBoardDTO> list = dao.selectList();
%>

	<h1>MYBOARD :: SELECT_LIST</h1>
	
	<table border="1">
		<col width="50">
		<col width="100">
		<col width="200">
		<col width="100">
	
		<tr>
			<th>번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
		
		<%
			for(int i=0; i<list.size(); i++) {
		%>
		
		<tr>
			<td><%= list.get(i).getMyNo() %></td>
			<td><%= list.get(i).getMyName() %></td>
			<td><a href="mydetail.jsp?MYNO=<%= list.get(i).getMyNo() %>"><%= list.get(i).getMyTitle() %></a></td>
			<td><%= list.get(i).getMyDate() %></td>
		</tr>		
		
		<%
			}
		%>
		
		<tr>
			<td colspan="4" align="right">
				<input type="button" onclick="location.href='myinsert.jsp'" value="작성"/>
			</td>
		</tr>
		
	</table>

</body>
</html>