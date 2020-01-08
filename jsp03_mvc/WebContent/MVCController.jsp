<%@page import="com.mvc.DAO.MVCBoardDAO"%>
<%@page import="com.mvc.DTO.MVCBoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.mvc.biz.MVCBoardBizImpl"%>
<%@page import="com.mvc.biz.MVCBoardBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	String command = request.getParameter("command");
	MVCBoardBiz biz = new MVCBoardBizImpl(); 

	if(command.equals("list")) {
		// 1. 받을 데이터가 있는지? -> 없음
		// 2. DB에서 가져올 데이터가 있는지?
		List<MVCBoardDTO> list = biz.selectList();
		request.setAttribute("boardList", list);
		
		// 3. 어디로 갈건지?
		pageContext.forward("boardList.jsp");	// 데이터를 담아서 보낼 때 많이 씀
	}
	
	else if(command.equals("writeForm")) {
		// 1. -> 없음
		// 2. -> 없음
		
		// 3. 어디로 갈건지?
		response.sendRedirect("boardWrite.jsp");	// 단순 화면 전달할 때 많이 씀
	}
	
	else if (command.equals("muldel")) {
		String chks[] = request.getParameterValues("chk");
		
		if(chks == null || chks.length == 0) {

%>

		<script type="text/javascript">
			alert("삭제할 글을 1개 이상 선택해 주세요!");
			location.href="boardList.jsp";
		</script>

<%
		} else {
		 	boolean res = false;
		 	res = biz.multiDelete(chks);
		 	
		}
	}
%>

</body>
</html>