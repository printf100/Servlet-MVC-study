<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LIST</title>

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
<body>

	<h1>일정 목록</h1>
	
	<form action="calendar.do" method="post">
		<input type="hidden" name="command" value="muldel">
		<jsp:useBean id="util" class="com.cal.DAO.Util"></jsp:useBean>
		
		<table border="1">
			<col width="50">
			<col width="50">
			<col width="300">
			<col width="300">
			<col width="150">
			
			<tr>
				<th><input type="checkbox" name="all" onclick="allChk(this.checked);"></th>
				<th>번호</th>
				<th>제목</th>
				<th>일정</th>
				<th>작성일</th>
			</tr>
			
			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="5">일정이 없습니다</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${list }" var="dto">
						<tr>
							<td><input type="checkbox" name="chk" value="${dto.seq }"></td>
							<td>${dto.seq }</td>
							<td><a href="">${dto.title }</a></td>
							<td>
								<jsp:setProperty property="todates" name="util" value="${dto.mDate }"/>
								<jsp:getProperty property="todates" name="util"/>
							</td>
							<td><fmt:formatDate value="${dto.regDate }" pattern="yyyy년MM월dd일"></fmt:formatDate></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			
			<tr>
				<td colspan="5">
					<input type="submit" value="선택삭제">
					<input type="button" value="돌아가기" onclick="">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>