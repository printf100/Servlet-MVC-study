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

</head>

<body>

	<h1>ANSWERBOARD :: 글 목록</h1>
	
		<table border="1">
			<col width="50">
			<col width="50">
			<col width="50">
			<col width="50">
			<col width="500">
			<col width="100">
			<col width="100">
			<tr>
				<th>글번호</th>
				<th>그룹번호</th>
				<th>그룹순서</th>
				<th>탭</th>
				<th>제목</th>
				<th>작성자</th>
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
							<td>${list.boardNo }</td>
							<td>${list.groupNo }</td>
							<td>${list.groupOrder }</td>
							<td>${list.titleTab }</td>
							<td>
								<c:if test="${list.titleTab > 0}">
									<c:forEach begin="1" end="${list.titleTab }">
										&nbsp;&nbsp;
									</c:forEach>
									└
								</c:if>
								<a href="mydetail?BOARDNO=${list.boardNo }">${list.title }</a>
							</td>
							<td>${list.writer }</td>
							<td>${list.regDate }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>

			<tr>
				<td colspan="7" align="center">
					<input type="button" value="글쓰기" onclick="location.href='mywriteForm'">
				</td>
			</tr>
			
		</table>

</body>
</html>