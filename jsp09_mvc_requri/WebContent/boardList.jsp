<%@page import="com.mvc.DTO.MVCBoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>

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

<body>

	<h1>MVCBOARD :: 글 목록</h1>
	
	<form action="con.do" method="post">
		<input type="hidden" name="command" value="multiDelete">
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
			
			<c:choose>
				<c:when test="${empty boardList }">
					<tr>
						<th colspan="5">작성된 글이 없습니다.</th>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${boardList }" var="list">
						<tr>
							<td><input type="checkbox" name="chk" value="${list.seq }"></td>
							<td>${list.seq }</td>
							<td>${list.writer }</td>
							<td><a href="mydetail?SEQ=${list.seq }">${list.title }</a></td>
							<td>${list.regDate }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>

			<tr>
				<td colspan="5" align="right">
					<input type="submit" value="선택삭제">
					<input type="button" value="글쓰기" onclick="location.href='mywriteForm'">
				</td>
			</tr>
			
		</table>
	</form>

</body>
</html>