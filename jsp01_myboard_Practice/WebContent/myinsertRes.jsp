<%@page import="DTO.MyBoardDTO"%>
<%@page import="DAO.MyBoardDAOImpl"%>
<%@page import="DAO.MyBoardDAO"%>
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
	String myName = request.getParameter("MYNAME");
	String myTitle = request.getParameter("MYTITLE");
	String myContent = request.getParameter("MYCONTENT");
	
	MyBoardDTO dto = new MyBoardDTO();
	dto.setMyName(myName);
	dto.setMyTitle(myTitle);
	dto.setMyContent(myContent);
	
	MyBoardDAO dao = new MyBoardDAOImpl();
	int res = dao.insert(dto);

	if(res > 0) {
%>

	<script type="text/javascript">
		alert("글 작성 성공!");
		location.href = "mylist.jsp";
	</script>
	
<%
	} else {
%>

	<script type="text/javascript">
		alert("글 작성 실패 ,,");
		location.href = "myinsert.jsp";
	</script>

<%
	}
%>

</body>
</html>