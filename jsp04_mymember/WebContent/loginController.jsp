<%@page import="java.util.List"%>
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
	
	// 전체 회원정보
	else if(command.equals("selectList")) {
		List<MyMemberDTO> list = biz.selectList();
		request.setAttribute("userList", list);
		
		pageContext.forward("userList.jsp");
	}
	
	// enabled=Y 인 회원정보
	else if(command.equals("selectEnabled")) {
		List<MyMemberDTO> list = biz.selecteEnabled();
		request.setAttribute("enabledUserList", list);
		
		pageContext.forward("userEnabledList.jsp");
	}
	
	// 등급 변경하는 화면으로
	else if(command.equals("updateRoleForm")) {
		int MYNO = Integer.parseInt(request.getParameter("MYNO"));
		
		MyMemberDTO dto = biz.selectOneUser(MYNO);
		request.setAttribute("selectOneUser", dto);
		
		pageContext.forward("updateRole.jsp");
	}
	
	// 등급 변경 완료
	else if(command.equals("updateRoleRes")) {
		
		int MYNO = Integer.parseInt(request.getParameter("MYNO"));
		String MYROLE = request.getParameter("MYROLE");
		
		int res = biz.updateRole(MYNO, MYROLE);
		
		if(res > 0) {
%>
		<script type="text/javascript">
			alert("등급 변경 완료");
			location.href="loginController.jsp?command=selectEnabled";
		</script>
<%
		} else {
%>
		<script type="text/javascript">
			alert("등급 변경 실패");
			location.href="loginController.jsp?command=selectEnabled?MYNO=" + MYNO;
		</script>
<%
		}
	}
	
	// 회원가입 화면으로
	else if(command.equals("join")) {
		response.sendRedirect("insertUser.jsp");
	}
	
	// 회원가입 완료
	else if(command.equals("joinRes")) {
		MyMemberDTO dto = new MyMemberDTO();
		dto.setId(request.getParameter("ID"));
		dto.setPw(request.getParameter("PW"));
		dto.setName(request.getParameter("NAME"));
		dto.setAddr(request.getParameter("ADDR"));
		dto.setPhone(request.getParameter("PHONE"));
		dto.setEmail(request.getParameter("EAMIL"));
		dto.setEnabled("Y");
		dto.setMyRole(request.getParameter("MYROLE"));
		
		int res = biz.insertUser(dto);
		
		if(res > 0) {
	
%>
		<script type="text/javascript">
			alert("회원가입 완료!");
			location.href="index.jsp";
		</script>
<%
		} else {
%>
		<script type="text/javascript">
			alert("회원가입 실패");
			location.href="insertUser.jsp";
		</script>
<%
		}
	}
%>

	<h1 style="color:plum;">잘못 왔따</h1>

</body>
</html>