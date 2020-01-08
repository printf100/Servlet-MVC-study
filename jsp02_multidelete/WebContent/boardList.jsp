<%@page import="java.util.ArrayList"%>
<%@page import="com.md.DTO.MDBoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.md.DAO.MDBoardDAOImpl"%>
<%@page import="com.md.DAO.MDBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록</title>

	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">
	
		function writeForm() {
			location.href="boardWriteForm.jsp";
		}

		function allChk(checked) {
			
			var chks = document.getElementsByName("chk");
			
			for(var i=0; i<chks.length; i++) {
				if(checked) {
					chks[i].checked = true;
				} else if(!checked) {
					chks[i].checked = false;
				}
			}
		}
		
	</script>

</head>

<%
	MDBoardDAO dao = new MDBoardDAOImpl();
	List<MDBoardDTO> list = dao.selectList();
%>

<body>

	<%@ include file="./form/header.jsp" %>

	<h1>MDBOARD :: SELECT_LIST</h1>
	
	<form action="./mulDel.jsp" method="post">
	
		<table border="1">
			<col width="30">
			<col width="50">
			<col width="100">
			<col width="300">
			<col width="100">
		
			<tr>
				<th><input type="checkbox" name="all" onclick="allChk(this.checked);"></th>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
			</tr>
			
			<%
				if(list.size() == 0) {
			%>
			
			<tr>
				<td colspan="5">------------- 작성된 글이 없습니다 --------------</td>
			</tr>
			
			<%
				} else {
	
					for(MDBoardDTO dto : list) {
			%>
			
			<tr>
				<td><input type="checkbox" name="chk" value="<%= dto.getSeq() %>"></td>
				<td><%= dto.getSeq() %></td>
				<td><%= dto.getWriter() %></td>
				<td><a href="boardContent.jsp?SEQ=<%= dto.getSeq() %>"><%= dto.getTitle() %></a></td>
				<td><%= dto.getRegDate() %></td>
			</tr>		
			
			<%
					}
				}
			%>
			
			<tr>
				<td colspan="5" align="right">
					<input type="submit" value="선택삭제">
					<input type="button" onclick="writeForm();" value="작성"/>
				</td>
			</tr>
			
		</table>
		
	</form>
	
	<%@ include file="./form/footer.jsp" %>

</body>
</html>