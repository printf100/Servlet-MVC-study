<%@page import="com.my.dao.MyDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 삭제 결과</title>
</head>
<body>

<%
	int myNo = Integer.parseInt(request.getParameter("MYNO"));
	
	MyDAO dao = new MyDAO();
	int result = dao.delete(myNo);
	
	if(result > 0) {
%>

	<script type="text/javascript">
		alert("글 삭제 성공!");
		location.href="mylist.jsp";
	</script>
	
<%
	} else {
%>

	<script type="text/javascript">
		alert("글 삭제 실패!");
		location.href="mydetail.jsp";
	</script>

<%
	}
%>

</body>
</html>