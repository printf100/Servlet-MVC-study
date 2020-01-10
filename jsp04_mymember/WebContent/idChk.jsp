<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 중복체크</title>

	<script type="text/javascript">
	
		onload = function() {
			var ID = opener.document.getElementsByName("ID")[0].value;
			document.getElementsByName("ID")[0].value = ID;
		}
	
		function myConfirm(bool) {
			if(bool == "true") {
				opener.document.getElementsByName("PW")[0].focus();
				opener.document.getElementsByName("ID")[0].title = 'y';
			} else {
				opener.document.getElementsByName("ID")[0].focus();
			}
			
			self.close();
		}
	
	</script>

</head>

<%
	String idNotUsed = request.getParameter("idNotUsed");
%>

<body>

	<table>
		<tr>
			<td><input type="text" name="ID"></td>
		</tr>
		<tr>
			<td><%= idNotUsed.equals("true") ? "아이디 생성 가능합니다." : "중복된 아이디가 존재합니다." %></td>
		</tr>
		<tr>
			<td>
				<input type="button" value="확인" onclick="myConfirm('<%= idNotUsed %>');">
			</td>
		</tr>
	</table>

</body>
</html>