<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

	<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">
		
		function idChkConfirm() {
			var chk = document.getElementsByName("ID")[0].title;
			
			if(chk == 'n') {
				alert("ID 중복체크를 먼저 해주세요!");
				document.getElementsByName("ID")[0].focus();
			}
		}

		function idChk() {
			var doc = document.getElementsByName("ID")[0];
			
			if(doc.value.trim() == "" || doc.value == null) {
				alert("아이디를 입력해주세요!");
			} else {
				open("loginController.jsp?command=idChk&ID=" + doc.value, "", "width=200,height=200");
			}
		}
	
		$(function() {
			// 아이디 중복확인 ajax
			$(".idChk").click(function() {
				
				$.ajax({
					url: "loginController.jsp?command=ajaxIdChk&ID="+ $("#ID").val(),
					type: "get",
					success: function(bool) {	// bool : 컨트롤러에서 넘겨준 결과값
						alert(bool);
						if(bool == "false") {
							$("#msg").text("사용 불가");
							$("#msg").attr("style", "color:red");
							
							$("#joinBtn").attr("disabled", "disabled");
						} else if(bool == "true") {
							$("#msg").text("사용 가능");
							$("#msg").attr("style", "color:blue");
							
							$("#joinBtn").removeAttr("disabled");
						}
					},
					error: function(request, status, error) {
						alert("통신 실패");
						alert("code : " + request.status + "\n" +
								"message : " + request.responseText + "\n" +
								"error : " + error);
					}
				})
			})
			
			// 비밀번호 일치 확인
			$(function() {
				$("#pwConfirm").blur(function() {
					if($("#pw").val() != $("#pwConfirm").val()) {
						if($("#pwConfirm").val() != "") {
							alert("비밀번호가 일치하지 않습니다.");
							$("#pwConfirm").val("");
							$("#pwConfirm").focus();
						}
					}
				})
			})
		})
		
	
	</script>

</head>
<body>

	<h1>MYMEMBER :: 회원가입</h1>
	
	<form action="loginController.jsp" method="post">
		<input type="hidden" name="command" value="joinRes">
		<table border="1">
			<col width="120">
			<col width="100">
			<tr>
				<th>아이디</th>
				<th>
					<input type="text" name="ID" id="ID" title="n" required="required">
					<button type="button" class="idChk">중복확인</button>
				</th>
				<th class="result">
					<p id="msg">중복확인
				</th>
			</tr>
			<tr>
				<th>비밀번호</th>
				<th><input type="password" name="PW" id="pw" required="required" onclick="idChkConfirm();"></th>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<th><input type="password" id="pwConfirm"></th>
			</tr>
			<tr>
				<th>이름</th>
				<th><input type="text" name="NAME" required="required" onclick="idChkConfirm();"></th>
			</tr>
			<tr>
				<th>주소</th>
				<th><input type="text" name="ADDR" required="required" onclick="idChkConfirm();"></th>
			</tr>
			<tr>
				<th>전화번호</th>
				<th><input type="text" name="PHONE" required="required" onclick="idChkConfirm();"></th>
			</tr>
			<tr>
				<th>이메일</th>
				<th><input type="text" name="EMAIL" required="required" onclick="idChkConfirm();"></th>
			</tr>
			<tr>
				<th>회원구분</th>
				<th>
					<input type="radio" name="MYROLE" required="required" onclick="idChkConfirm();" value="USER">일반회원
					<input type="radio" name="MYROLE" required="required" onclick="idChkConfirm();" value="ADMIN">관리자
				</th>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="JOIN" id="joinBtn">
					<input type="button" value="CANCEL" onclick="location.href='index.jsp'">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>