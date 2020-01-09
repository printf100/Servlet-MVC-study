<%@page import="com.my.DTO.MyMemberDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.my.BIZ.MyMemberBizImpl"%>
<%@page import="com.my.BIZ.MyMemberBiz"%>
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
	String command = request.getParameter("command");
	System.out.println("[ " + command + " ]");
	
	MyMemberBiz biz = new MyMemberBizImpl();
	
	if(command.equals("selectList")) {
		List<MyMemberDTO> list = biz.selectList();
		request.setAttribute("userList", list);
		pageContext.forward("userList.jsp");
	}
	
	else if(command.equals("selectEnabled")) {
		List<MyMemberDTO> list = biz.selecteEnabled();
		request.setAttribute("enabledUserList", list);
		pageContext.forward("userEnabledList.jsp");
	}
%>

	<h1>COMMAND 잘못 씀</h1>

</body>
</html>