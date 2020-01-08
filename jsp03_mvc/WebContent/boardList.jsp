<%@page import="com.mvc.DTO.MVCBoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>

	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">

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
	List<MVCBoardDTO> list = (List<MVCBoardDTO>) request.getAttribute("boardList");	// Object로 리턴됐기 때문에 List로 명시적 형변환(downCasting) 해줘야 함!
%>

<body>

	<h1>MVCBOARD :: SELECT_LIST</h1>
	
	<form action="MVCController.jsp" method="post">
		<input type="hidden" name="command" value="muldel">
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
				<th colspan="5">작성된 글이 없습니다.</th>
			</tr>
<%
		} else {
			for(MVCBoardDTO dto : list) {
%>
			<tr>
				<td><input type="checkbox" name="chk" value="<%= dto.getSeq() %>"></td>
				<td><%= dto.getSeq() %></td>
				<td><%= dto.getWriter() %></td>
				<td><a href="MVCController.jsp?command=content&SEQ=<%= dto.getSeq() %>"><%= dto.getTitle() %></a></td>
				<td><%= dto.getRegDate() %></td>
			</tr>
<%
			}
		}
%>

			<tr>
				<td colspan="5" align="right">
					<input type="submit" value="선택삭제">
					<input type="button" value="글쓰기" onclick="location.href='MVCController.jsp?command=writeForm'">
				</td>
			</tr>
			
		</table>
	</form>

</body>
</html>