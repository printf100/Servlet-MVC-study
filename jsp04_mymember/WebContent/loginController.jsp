<%@page import="com.my.DTO.MyMemberDTO"%>
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
<title>로그인 처리</title>
</head>
<body>
<%
	String command = request.getParameter("command");
	System.out.println("[ " + command + " ]");
	
	MyMemberBiz biz = new MyMemberBizImpl();
	
	// 로그인
	if(command.equals("login")) {
		String ID = request.getParameter("ID");
		String PW = request.getParameter("PW");
		
		MyMemberDTO dto = biz.login(ID, PW);
		
		if(dto != null) {
			// session : 만료되기 전까지 어플리케이션 전체에서 사용 가능
			session.setAttribute("dto", dto);
			
			//setMaxInactiveInterval(second) : 해당 초만큼 활동이 없으면 session을 invalidate한다. (default:30분 / 음수:무제한)
			session.setMaxInactiveInterval(10*60);
			
			if(dto.getMyRole().equals("ADMIN")) {
				response.sendRedirect("adminMain.jsp");
			}
			
		} else {	// 로그인 실패했을 때
%>
			<script type="text/javascript">
				alert("ID와 PW를 다시 한 번 확인해주세요!");
				location.href="index.jsp";
			</script>
<%
		}
	}
	
	// 로그아웃
	else if(command.equals("logout")) {
		session.invalidate();	// 만료
		response.sendRedirect("index.jsp");
	}
	
	// 회원가입 화면으로
	else if(command.equals("join")) {
		response.sendRedirect("insertUser.jsp");
	}
%>

</body>
</html>