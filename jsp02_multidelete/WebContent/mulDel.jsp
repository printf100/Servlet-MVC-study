<%@page import="com.md.DAO.MDBoardDAOImpl"%>
<%@page import="org.apache.tomcat.util.security.MD5Encoder"%>
<%@page import="com.md.DAO.MDBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선택 삭제</title>
</head>

<%
	// mulDel.jsp?chk=1&chk=2&chk=5&...
	
	// getParameterValues() : 같은 이름의 여러 값을 받을 때 사용
	String[] chks = request.getParameterValues("chk");
	
	if(chks == null || chks.length == 0) {
%>

	<script type="text/javascript">
		alert("삭제할 글을 1개 이상 선택해 주세요!");
		location.href="boardList.jsp";
	</script>
	
<%
	} else {
		MDBoardDAO dao = new MDBoardDAOImpl();
		int res = dao.multiDelete(chks);
		
		if(res > 0) {
%>

	<script type="text/javascript">
		alert("선택한 글 삭제 성공!");
		location.href="boardList.jsp";
	</script>

<%
		} else {
%>

	<script type="text/javascript">
		alert("선택한 글 삭제 실패 ,,");
		location.href="boardList.jsp";
	</script>

<%
		}
	}
%>

<body>

</body>
</html>