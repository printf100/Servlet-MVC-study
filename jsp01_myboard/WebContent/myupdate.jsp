<%@page import="com.my.dto.MyDTO"%>
<%@page import="com.my.dao.MyDAO"%>
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
<body>

<%
	int myNo = Integer.parseInt(request.getParameter("MYNO"));
	
	MyDAO dao = new MyDAO();
	MyDTO dto = dao.selectOne(myNo);
%>

	<h1>글 수정하기</h1>
	
	<form action="myupdateRes.jsp?MYNO=<%= myNo %>" method="post">
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
					<input type="button" value="취소" onclick="">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>