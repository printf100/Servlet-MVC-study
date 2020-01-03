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
	int res = dao.delete(myNo);

	if(res > 0) {
%>

	<script type="text/javascript">
		alert("글 삭제 성공!");
		location.href = "mylist.jsp";
	</script>
	
<%
	} else {
%>

	<script type="text/javascript">
		alert("글 삭제 실패 ,,");
		location.href = "mydetail.jsp?MYNO=<%= myNo %>";
	</script>

<%
	}
%>

</body>
</html>