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
<title>Insert title here</title>
</head>

<%
	int seq = Integer.parseInt(request.getParameter("SEQ"));

	MDBoardDAO dao = new MDBoardDAOImpl();
	int res = dao.delete(seq);
	
	if(res > 0) {
%>

	<script type="text/javascript">
		alert("글 삭제 성공!");
		location.href="boardList.jsp";
	</script>

<%
	} else {
%>

	<script type="text/javascript">
		alert("글 삭제 실패 ,,");
		location.href="boardContent.jsp?SEQ=<%= seq %>";
	</script>

<%
	}
%>

<body>

</body>
</html>