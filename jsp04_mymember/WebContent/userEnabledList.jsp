<%@page import="com.my.DTO.MyMemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보(ENABLED)</title>

	<script type="text/javascript">
	
		function updateRole(myno) {
			location.href="loginController.jsp?command=updateRoleForm&MYNO="+myno;
		}
	
	</script>

</head>

<%
	List<MyMemberDTO> list = (List<MyMemberDTO>)request.getAttribute("enabledUserList");
%>

<body>

	<h1>MYMEMBER :: 회원정보(ENABLED)</h1>
	
	<table border="1">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>주소</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>가입여부</th>
			<th>등급</th>
			<th>등급변경</th>
		</tr>
<%
		for(MyMemberDTO dto : list) {
%>
		<tr>
			<td><%= dto.getMyNo() %></td>
			<td><%= dto.getId() %></td>
			<td><%= dto.getPw() %></td>
			<td><%= dto.getName() %></td>
			<td><%= dto.getAddr() %></td>
			<td><%= dto.getPhone() %></td>
			<td><%= dto.getEmail() %></td>
			<td><%= dto.getEnabled().equals("Y") ? "가입" : "탈퇴" %></td>
			<td><%= dto.getMyRole() %></td>
			<td><button onclick="updateRole(<%= dto.getMyNo() %>);">변경</button></td>
		</tr>
<%
		}
%>
		<tr>
			<td colspan="9">
				<button onclick="location.href='adminMain.jsp'">메인으로</button>
			</td>
		</tr>
	</table>

</body>
</html>