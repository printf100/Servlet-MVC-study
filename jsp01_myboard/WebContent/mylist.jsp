<%@page import="com.my.dto.MyDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.my.dao.MyDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>

<!-- 응답받는 형태는 text로 된 html파일이고, UTF-8로 인코딩된다. -->
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mylist</title>
</head>
<body>

<%
	MyDAO dao = new MyDAO();
	List<MyDTO> list = dao.selectList();
%>

	<h1>List</h1>
	
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
			<td><a href="#"><%= list.get(i).getMyTitle() %></a></td>
			<td><%= list.get(i).getMyDate() %></td>
		</tr>
		
		<%
			}
		%>
		
		<tr>
			<td colspan="4" align="right">
				<input type="button" onclick="" value="글쓰기">
			</td>
		</tr>
		
	</table>

</body>
</html>