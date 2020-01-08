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

	// 글 목록
	if(command.equals("list")) {
		// 1. 받을 데이터가 있는지? -> 없음
				
		// 2. DB에서 가져올 데이터가 있는지?
		List<MVCBoardDTO> list = biz.selectList();
		request.setAttribute("boardList", list);
		
		// 3. 어디로 갈건지?
		pageContext.forward("boardList.jsp");	// 데이터를 담아서 보낼 때 많이 씀, 서버가 모른다.
	}
	
	// 글 작성 폼
	else if(command.equals("writeForm")) {
		// 1. -> 없음
		// 2. -> 없음
		
		// 3. 어디로 갈건지?
		response.sendRedirect("boardWrite.jsp");	// 단순 화면 전달할 때 많이 씀, 서버가 안다.
		// command=writeForm을 응답하다가 다시 boardWrite.jsp를 요청
	}
	
	// 글쓰기 완료
	else if(command.equals("writeRes")) {
		// 1.
		MVCBoardDTO dto = new MVCBoardDTO();
		dto.setWriter(request.getParameter("WRITER"));
		dto.setTitle(request.getParameter("TITLE"));
		dto.setContent(request.getParameter("CONTENT"));
		
		// 2.
		int res = biz.insert(dto);
		
		// 3.
		if(res > 0) {
%>
			<script type="text/javascript">
				alert("새로운 글 등록을 완료하였습니다.");
				location.href="MVCController.jsp?command=list";
			</script>
<%
		} else {
%>
			<script type="text/javascript">
				alert("글 등록을 실패하였습니다.");
				location.href="MVCController.jsp?command=writeForm";
			</script>
<%
		}
	}
	
	// 여러 글 선택삭제
	else if(command.equals("muldel")) {
		// 1.
		String chks[] = request.getParameterValues("chk");
		
		if(chks == null || chks.length == 0) {
%>
			<script type="text/javascript">
				alert("삭제할 글을 1개 이상 선택해 주세요!");
				location.href="boardList.jsp";
			</script>
<%
		} else {
			// 2.
		 	boolean res = biz.multiDelete(chks);
			
			// 3.
		 	if(res) {
%>
				<script type="text/javascript">
					alert("선택한 글 삭제를 완료하였습니다.");
					location.href="MVCController.jsp?command=list";
				</script>
<%
		 	}
		}
	}
	
	// 글 내용
	else if(command.equals("content")) {
		// 1.
		int seq = Integer.parseInt(request.getParameter("SEQ"));
		
		// 2.
		MVCBoardDTO dto = biz.selectOne(seq);
		request.setAttribute("boardContent", dto);
		
		// 3.
		pageContext.forward("boardContent.jsp");
	}
	
	// 글 수정 폼
	else if(command.equals("updateForm")) {
		// 1.
		int seq = Integer.parseInt(request.getParameter("SEQ"));
		
		// 2.
		MVCBoardDTO dto = biz.selectOne(seq);
		request.setAttribute("boardContent", dto);
		
		// 3. 어디로 갈건지?
		pageContext.forward("boardUpdate.jsp");
	}
	
	// 글 수정 완료
	else if(command.equals("updateRes")) {
		// 1.
		int seq = Integer.parseInt(request.getParameter("SEQ"));
		
		MVCBoardDTO dto = new MVCBoardDTO();
		dto.setWriter(request.getParameter("WRITER"));
		dto.setTitle(request.getParameter("TITLE"));
		dto.setContent(request.getParameter("CONTENT"));
		dto.setSeq(seq);
		
		// 2.
		int res = biz.update(dto);
		
		// 3.
		if(res > 0) {
%>
			<script type="text/javascript">
				alert("글 수정을 완료하였습니다.");
				location.href="MVCController.jsp?command=list";
			</script>
<%
		} else {
%>
			<script type="text/javascript">
				alert("글 수정을 실패하였습니다.");
				location.href="MVCController.jsp?command=updateForm";
			</script>
<%
		}
	}
	
	// 글 삭제 완료
	else if(command.equals("deleteRes")) {
		// 1.
		int seq = Integer.parseInt(request.getParameter("SEQ"));
		
		// 2.
		int res = biz.delete(seq);
		
		// 3.
		if(res > 0) {
%>
			<script type="text/javascript">
				alert("글 삭제를 완료하였습니다.");
				location.href="MVCController.jsp?command=list";
			</script>
<%
		} else {
%>
			<script type="text/javascript">
				alert("글 삭제를 실패하였습니다.");
				location.href="MVCController.jsp?command=content";
			</script>
<%
		}
	}
%>

	<h1>command 잘못썼어</h1>

</body>
</html>