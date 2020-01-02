<%@page import="com.my.dao.MyDAO"%>
<%@page import="com.my.dto.MyDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정 결과</title>
</head>
<body>

<%
	String myName = request.getParameter("MYNAME");
	String myTitle = request.getParameter("MYTITLE");	
	String myContent = request.getParameter("MYCONTENT");
	int myNo = Integer.parseInt(request.getParameter("MYNO"));
	
	MyDTO dto = new MyDTO();
	dto.setMyName(myName);
	dto.setMyTitle(myTitle);
	dto.setMyContent(myContent);
	dto.setMyNo(myNo);
	
	MyDAO dao = new MyDAO();
	int result = dao.update(dto);
	
	if(result > 0) {
%>

	<script type="text/javascript">
		alert("글 수정 성공!");
		location.href = "mylist.jsp";
	</script>

<%
	} else {
%>

	<script type="text/javascript">
		alert("글 수정 실패 ,,");
		location.href = "myupdate.jsp";
	</script>

<%
	}
%>

</body>
</html>