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
	String writer = request.getParameter("WRITER");
	String title = request.getParameter("TITLE");
	String content = request.getParameter("CONTENT");
	int seq = Integer.parseInt(request.getParameter("SEQ"));
	
	MDBoardDTO dto = new MDBoardDTO();
	dto.setWriter(writer);
	dto.setTitle(title);
	dto.setContent(content);
	dto.setSeq(seq);
	
	MDBoardDAO dao = new MDBoardDAOImpl();
	int res = dao.update(dto);
	
	if(res > 0) {
%>

	<script type="text/javascript">
		alert("글 수정 성공!");
		location.href="boardContent.jsp?SEQ=<%= seq %>";
	</script>

<%
	} else {
%>

	<script type="text/javascript">
		alert("글 수정 실패 ,,");
		location.href="boardUpdate.jsp?SEQ=<%= seq %>";
	</script>

<%
	}
%>

<body>

</body>
</html>