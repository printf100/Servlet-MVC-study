package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class MVCController
 */
@WebServlet("/board.do")
public class MVCController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String view;
	private PrintWriter out;
	
	MVCBoardBiz biz = new MVCBoardBizImpl();
       
    public MVCController() {
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
    	
    	this.request = request;
    	this.response = response;
    	
    	request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		out = response.getWriter();
		String command = request.getParameter("command");
		System.out.println("[ " + command + " ]");
    
		if(command == null) {
			command = "boardList";
		}
		
		switch(command) {
		
		// 전체 글 목록
		case "boardList":
			selectList();
			break;
			
		// 글 내용
		case "boardContent":
			selectOne();
			break;
			
		// 글 작성 화면으로
		case "writeForm":
			writeForm();
			break;
			
		// 글 작성
		case "boardWrite":
			insert();
			return;
		
		// 글 수정 화면으로
		case "updateForm":
			updateForm();
			break;
		
		// 글 수정
		case "boardUpdate":
			update();
			return;
			
		// 글 삭제
		case "boardDelete":
			delete();
			return;
		
		// 글 여러개 삭제
		case "multiDelete":
			muldel();
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
    }

	// 전체 글 목록
	private void selectList() {
		request.setAttribute("boardList", biz.selectList());
		view = "/boardList.jsp";
	}

	// 글 내용
	private void selectOne() {
		int seq = Integer.parseInt(request.getParameter("SEQ"));
		
		request.setAttribute("boardContent", biz.selectOne(seq));
		view = "/boardContent.jsp";
	}
	
	private void writeForm() {
		view = "/boardWrite.jsp";
	}

	// 글 작성
	private void insert() {
		
		MVCBoardDTO dto = new MVCBoardDTO();
		
		String WRITER = request.getParameter("WRITER");
		String TITLE = request.getParameter("TITLE");
		String CONTENT = request.getParameter("CONTENT");
		
		dto.setWriter(WRITER);
		dto.setTitle(TITLE);
		dto.setContent(CONTENT);
		
		if(biz.insert(dto) > 0) {
			System.out.println("글 쓰기 성공!");
			
			out.println("<script>");
			out.println("alert('글 쓰기 성공!');");
			out.println("location.href='board.do?command=boardList';");
			out.println("</script>");
			out.close();
			//view = "MVCController?command=boardList";
		} else {
			System.out.println("MVCController : insert() - 글 쓰기 실패");
			
			out.println("<script>");
			out.println("alert('글 쓰기 실패');");
			out.println("location.href='boardWrite.jsp';");
			out.println("</script>");
			out.close();
			//view = "/boardWrite.jsp";
		}
	}
	
	private void updateForm() {
		int seq = Integer.parseInt(request.getParameter("SEQ"));
		
		request.setAttribute("boardContent", biz.selectOne(seq));
		view = "/boardUpdate.jsp";
	}

	// 글 수정
	private void update() {
		MVCBoardDTO dto = new MVCBoardDTO();
		
		String WRITER = request.getParameter("WRITER");
		String TITLE = request.getParameter("TITLE");
		String CONTENT = request.getParameter("CONTENT");
		int seq = Integer.parseInt(request.getParameter("SEQ"));
		
		dto.setWriter(WRITER);
		dto.setTitle(TITLE);
		dto.setContent(CONTENT);
		dto.setSeq(seq);
		
		if(biz.update(dto) > 0) {
			System.out.println("글 수정 성공!");
			
			out.println("<script>");
			out.println("alert('글 수정 성공!');");
			out.println("location.href='board.do?command=boardList';");
			out.println("</script>");
			out.close();
			//view = "MVCController?command=boardContent&SEQ=" + seq;
		} else {
			System.out.println("MVCController : update() - 글 수정 실패");
			
			out.println("<script>");
			out.println("alert('글 수정 실패');");
			out.println("location.href='board.do?command=updateForm&SEQ='" + seq);
			out.println("</script>");
			out.close();
			//view = "MVCController?command=updateForm&SEQ=" + seq;
		}
	}

	private void delete() {
		int seq = Integer.parseInt(request.getParameter("SEQ"));
		
		if(biz.delete(seq) > 0) {
			System.out.println("글 삭제 성공!");
			
			out.println("<script>");
			out.println("alert('글 삭제 성공!');");
			out.println("location.href='board.do?command=boardList';");
			out.println("</script>");
			out.close();
			//view = "MVCController?command=boardList";
		} else {
			System.out.println("MVCController : delete() - 글 삭제 실패");
			
			out.println("<script>");
			out.println("alert('글 삭제 실패');");
			out.println("location.href='board.do?command=boardContent&SEQ='" + seq);
			out.println("</script>");
			out.close();
			//view = "MVCController?command=boardContent&SEQ=" + seq;
		}
	}

	private void muldel() {
		
		String chks[] = request.getParameterValues("chk");
		
		if(chks == null || chks.length == 0) {
			out.println("<script>");
			out.println("alert('삭제할 글을 한 개 이상 선택해주세요!');");
			out.println("location.href='board.do?command=boardList';");
			out.println("</script>");
			out.close();
		} else {
			if(biz.multiDelete(chks)) {
				System.out.println("글 여러개 삭제 성공!");
				
				out.println("<script>");
				out.println("alert('글 여러개 삭제 성공!');");
				out.println("location.href='board.do?command=boardList';");
				out.println("</script>");
				out.close();
				//view = "MVCController?command=boardList";
			} else {
				System.out.println("MVCController : muldel() - 글 여러개 삭제 실패");
				
				out.println("<script>");
				out.println("alert('글 여러개 삭제 실패');");
				out.println("location.href='board.do?command=boardList';");
				out.println("</script>");
				out.close();
				//view = "MVCController?command=boardList";
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
