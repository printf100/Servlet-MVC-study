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

@WebServlet("/con.do")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public MyServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("[ " + command + " ]");
		
		MVCBoardBiz biz = new MVCBoardBizImpl();
		
		if(command.equals("boardList")) {
			List<MVCBoardDTO> list = biz.selectList();
			request.setAttribute("boardList", list);
			dispatch("boardList.jsp", request, response);
		}
		
		else if(command.equals("writeForm")) {
			dispatch("boardWrite.jsp", request, response);
		}
		
		else if(command.equals("boardWrite")) {
			String WRITER = request.getParameter("WRITER");
			String TITLE = request.getParameter("TITLE");
			String CONTENT = request.getParameter("CONTENT");
			
			MVCBoardDTO dto = new MVCBoardDTO();
			dto.setWriter(WRITER);
			dto.setTitle(TITLE);
			dto.setContent(CONTENT);
			
			if(biz.insert(dto) > 0) {
				jsResponse("글 쓰기 성공!", "con.do?command=boardList", response);
			} else {
				jsResponse("글 쓰기 실패", "con.do?command=writeForm", response);
			}
		}
		
		else if(command.equals("boardContent")) {
			int SEQ = Integer.parseInt(request.getParameter("SEQ"));
			MVCBoardDTO dto = biz.selectOne(SEQ);
			request.setAttribute("boardContent", dto);
			dispatch("boardContent.jsp", request, response);
		}
		
		else if(command.equals("updateForm")) {
			int SEQ = Integer.parseInt(request.getParameter("SEQ"));
			MVCBoardDTO dto = biz.selectOne(SEQ);
			request.setAttribute("boardContent", dto);
			dispatch("boardUpdate.jsp", request, response);
		}
		
		else if(command.equals("boardUpdate")) {
			String WRITER = request.getParameter("WRITER");
			String TITLE = request.getParameter("TITLE");
			String CONTENT = request.getParameter("CONTENT");
			int SEQ = Integer.parseInt(request.getParameter("SEQ"));
			
			MVCBoardDTO dto = new MVCBoardDTO();
			dto.setWriter(WRITER);
			dto.setTitle(TITLE);
			dto.setContent(CONTENT);
			dto.setSeq(SEQ);
			
			if(biz.update(dto) > 0) {
				jsResponse("글 수정 성공!", "con.do?command=boardContent&SEQ="+SEQ, response);
			} else {
				jsResponse("글 수정 실패", "con.do?command=updateForm&SEQ="+SEQ, response);
			}
		}
		
		else if(command.equals("boardDelete")) {
			int SEQ = Integer.parseInt(request.getParameter("SEQ"));
			
			if(biz.delete(SEQ) > 0) {
				jsResponse("글 삭제 성공!", "con.do?command=boardList", response);
			} else {
				jsResponse("글 삭제 실패", "con.do?command=boardContent?SEQ="+SEQ, response);
			}
		}
		
		response.getWriter().append("<h1><a href='con.do?command=boardList'>잘못왔다</a></h1>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		doGet(request, response);
	}

	// alert 띄워주는 메소드
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('" + msg + "')");
		out.println("location.href='" + url + "'");
		out.println("</script>");
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
