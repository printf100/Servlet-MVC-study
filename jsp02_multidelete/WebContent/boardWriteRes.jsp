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
<title>글 작성 결과</title>
</head>

<%
	String writer = request.getParameter("WRITER");
	String title = request.getParameter("TITLE");
	String content = request.getParameter("CONTENT");

	MDBoardDTO dto = new MDBoardDTO();
	dto.setWriter(writer);
	dto.setTitle(title);
	dto.setContent(content);
	
	MDBoardDAO dao = new MDBoardDAOImpl();
	int res = dao.insert(dto);
	
	if(res > 0) {
%>

	<script type="text/javascript">
		alert("글 작성 성공!");
		location.href="boardList.jsp";
	</script>

<%
	} else {
%>

	<script type="text/javascript">
		alert("글 작성 실패 ,,");
		location.href="boardWriteForm.jsp";
	</script>

<%
	}
%>

<body>

</body>
</html>