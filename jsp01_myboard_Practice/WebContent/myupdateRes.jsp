<%@page import="DAO.MyBoardDAO"%>
<%@page import="DAO.MyBoardDAOImpl"%>
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
	String myName = request.getParameter("MYNAME");
	String myTitle = request.getParameter("MYTITLE");
	String myContent = request.getParameter("MYCONTENT");
	
	MyBoardDTO dto = new MyBoardDTO();
	dto.setMyName(myName);
	dto.setMyTitle(myTitle);
	dto.setMyContent(myContent);
	dto.setMyNo(myNo);
	
	MyBoardDAO dao = new MyBoardDAOImpl();
	int res = dao.update(dto);

	if(res > 0) {
%>

	<script type="text/javascript">
		alert("글 수정 성공!");
		location.href = "mydetail.jsp?MYNO=<%= dto.getMyNo() %>";
	</script>
	
<%
	} else {
%>

	<script type="text/javascript">
		alert("글 수정 실패 ,,");
		location.href = "myupdate.jsp?MYNO=<%= dto.getMyNo() %>";
	</script>

<%
	}
%>

</body>
</html>