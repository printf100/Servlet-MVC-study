package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.DTO.MVCBoardDTO;
import com.mvc.biz.MVCBoardBiz;
import com.mvc.biz.MVCBoardBizImpl;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet(urlPatterns = {"/mylist", "/mydetail", "/mywriteForm", "/mywriteRes", "/myupdateForm", "/myupdateRes", "/mydelete"})
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	MVCBoardBiz biz = null;
	
	private void getRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		biz = new MVCBoardBizImpl();
		
		String command = request.getRequestURI();
		System.out.printf("[ %s ]\n", command);
		
		if(command.endsWith("/mylist")) {
			doMyList(request, response);
		}
		
		else if(command.endsWith("/mydetail")) {
			doMyDetail(request, response);
		}
		
		else if(command.endsWith("/mywriteForm")) {
			doMyWrite(request, response);
		}
		
		else if(command.endsWith("/mywriteRes")) {
			doMyWriteRes(request, response);
		}
		
		else if(command.endsWith("/myupdateForm")) {
			doMyUpdate(request, response);
		}
		
		else if(command.endsWith("/myupdateRes")) {
			doMyUpdateRes(request, response);
		}
		
		else if(command.endsWith("/mydelete")) {
			doMyDelete(request, response);
		}
	}

	private void doMyList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MVCBoardDTO> list = biz.selectList();
		request.setAttribute("boardList", list);
		dispatch("boardList.jsp", request, response);
	}
	
	private void doMyDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int SEQ = Integer.parseInt(request.getParameter("SEQ"));
		
		MVCBoardDTO dto = biz.selectOne(SEQ);
		request.setAttribute("dto", dto);
		dispatch("boardContent.jsp", request, response);
	}
	
	private void doMyWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("boardWrite.jsp");
	}
	
	private void doMyWriteRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String WRITER = request.getParameter("WRITER");
		String TITLE = request.getParameter("TITLE");
		String CONTENT = request.getParameter("CONTENT");
		
		MVCBoardDTO dto = new MVCBoardDTO(WRITER, TITLE, CONTENT);
		int res = biz.insert(dto);
		
		if(res > 0) {
			jsResponse("글 쓰기 성공!", "mylist", response);
		} else {
			jsResponse("글 쓰기 실패", "mywriteForm", response);
		}
	}
	
	private void doMyUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int SEQ = Integer.parseInt(request.getParameter("SEQ"));
		
		MVCBoardDTO dto = biz.selectOne(SEQ);
		request.setAttribute("dto", dto);
		dispatch("boardUpdate.jsp", request, response);
	}
	
	private void doMyUpdateRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String WRITER = request.getParameter("WRITER");
		String TITLE = request.getParameter("TITLE");
		String CONTENT = request.getParameter("CONTENT");
		int SEQ = Integer.parseInt(request.getParameter("SEQ"));
		
		MVCBoardDTO dto = new MVCBoardDTO(SEQ, WRITER, TITLE, CONTENT);
		int res = biz.update(dto);
		
		if(res > 0) {
			jsResponse("글 수정 성공!", "mydetail?SEQ="+SEQ, response);
		} else {
			jsResponse("글 수정 실패", "myupdateForm?SEQ="+SEQ, response);
		}
	}
	
	private void doMyDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int SEQ = Integer.parseInt(request.getParameter("SEQ"));
		
		int res = biz.delete(SEQ);
		
		if(res > 0) {
			jsResponse("글 삭제 성공!", "mylist", response);
		} else {
			jsResponse("글 삭제 실패", "mydetail?SEQ="+SEQ, response);
		}
	}

	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('" + msg + "')");
		out.println("location.href='" + url + "'");
		out.println("</script>");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		getRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		getRequest(request, response);
	}

}
