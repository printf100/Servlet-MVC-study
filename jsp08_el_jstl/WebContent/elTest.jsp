<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL TEST</title>
</head>
<body>

	<h1>EL Test</h1>
	
	<table border="1">
		<tr>
			<th colspan="2">${hong.name }님의 점수</th>
		</tr>
		<tr>
			<td>국어</td>
			<td>${hong.kor }</td>	<!-- .kor는 getKor()를 호출해줌 -->
		</tr>
		<tr>
			<td>영어</td>
			<td>${hong.eng }</td>
		</tr>
		<tr>
			<td>수학</td>
			<td>${hong.math }</td>
		</tr>
		<tr>
			<td>총점</td>
			<td>${hong.sum }</td>
		</tr>
		<tr>
			<td>평균</td>
			<td>${hong.avg }</td>
		</tr>
		<tr>
			<td>등급</td>
			<td>${hong.grade }</td>
		</tr>
	</table>

</body>
</html>