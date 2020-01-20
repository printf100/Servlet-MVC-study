package com.answer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.answer.DTO.AnswerBoardDTO;
import com.answer.biz.AnswerBoardBiz;
import com.answer.biz.AnswerBoardBizImpl;

/**
 * Servlet implementation class AnswerController
 */
@WebServlet(urlPatterns = {"/mylist", "/mydetail", "/mywriteForm", "/mywriteRes", "/myupdateForm", "/myupdateRes", "/myanswerForm", "/myanswerRes", "/mydelete"})
public class AnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AnswerBoardBiz biz = null;

	private void getRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		biz = new AnswerBoardBizImpl();
		
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
		
		else if(command.endsWith("/myanswerForm")) {
			doMyAnswer(request, response);
		}
		
		else if(command.endsWith("/myanswerRes")) {
			doMyAnswerRes(request, response);
		}
		
		else if(command.endsWith("/mydelete")) {
			doMyDelete(request, response);
		}
	}

	private void doMyList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<AnswerBoardDTO> list = biz.selectList();
		request.setAttribute("boardList", list);
		dispatch("boardList.jsp", request, response);
	}

	private void doMyDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int BOARDNO = Integer.parseInt(request.getParameter("BOARDNO"));
		AnswerBoardDTO dto = biz.selectOne(BOARDNO);
		request.setAttribute("dto", dto);
		dispatch("boardContent.jsp", request, response);
	}

	private void doMyWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatch("boardWrite.jsp", request, response);
	}

	private void doMyWriteRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String WRITER = request.getParameter("WRITER");
		String TITLE = request.getParameter("TITLE");
		String CONTENT = request.getParameter("CONTENT");
		
		AnswerBoardDTO dto = new AnswerBoardDTO();
		dto.setWriter(WRITER);
		dto.setTitle(TITLE);
		dto.setContent(CONTENT);
		
		int res = biz.insert(dto);
		if(res > 0) {
			jsResponse("글 쓰기 성공!", "mylist", response);
		} else {
			jsResponse("글 쓰기 실패 ,, 작성 화면으로 돌아갑니다", "mywriteForm", response);
		}
	}

	private void doMyUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int BOARDNO = Integer.parseInt(request.getParameter("BOARDNO"));
		AnswerBoardDTO dto = biz.selectOne(BOARDNO);
		request.setAttribute("dto", dto);
		dispatch("boardUpdate.jsp", request, response);
	}

	private void doMyUpdateRes(HttpServletRequest request, HttpServletResponse response) throws IOException {		
		String WRITER = request.getParameter("WRITER");
		String TITLE = request.getParameter("TITLE");
		String CONTENT = request.getParameter("CONTENT");
		int BOARDNO = Integer.parseInt(request.getParameter("BOARDNO"));
		
		AnswerBoardDTO dto = new AnswerBoardDTO();
		dto.setWriter(WRITER);
		dto.setTitle(TITLE);
		dto.setContent(CONTENT);
		dto.setBoardNo(BOARDNO);
		
		int res = biz.update(dto);
		if(res > 0) {
			jsResponse("글 수정 성공!", "mylist", response);
		} else {
			jsResponse("글 수정 실패 ,, 작성 화면으로 돌아갑니다", "mywriteForm?BOARDNO="+BOARDNO, response);
		}
	}
	
	// 답글 달기
	private void doMyAnswer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int BOARDNO = Integer.parseInt(request.getParameter("BOARDNO"));	// 부모글 번호
		String originalTITLE = "RE : " + biz.selectOne(BOARDNO).getTitle();
		request.setAttribute("originalTITLE", originalTITLE);
		request.setAttribute("BOARDNO", BOARDNO);
		dispatch("boardAnswer.jsp", request, response);
	}
	
	private void doMyAnswerRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int BOARDNO = Integer.parseInt(request.getParameter("BOARDNO"));	// 부모글 번호
		String WRITER = request.getParameter("WRITER");
		String TITLE = request.getParameter("TITLE");
		String CONTENT = request.getParameter("CONTENT");
		
		boolean updateOrderRes = biz.updateOrder(BOARDNO);
		// 그룹순서 +1 완료하면~
		if(updateOrderRes) {
			AnswerBoardDTO dto = new AnswerBoardDTO();
			dto.setWriter(WRITER);
			dto.setTitle(TITLE);
			dto.setContent(CONTENT);
			dto.setBoardNo(BOARDNO);
			
			int res = biz.insertAnswer(dto);
			if(res > 0) {
				jsResponse("답글 쓰기 성공!", "mylist", response);
			} else {
				jsResponse("답글 쓰기 실패 ,, 작성 화면으로 돌아갑니다", "myanswerForm?BOARDNO="+BOARDNO, response);
			}
		} else {
			jsResponse("그룹순서+1 실패 ,,", "mydetail?BOARDNO="+BOARDNO, response);
		}
	}

	private void doMyDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int BOARDNO = Integer.parseInt(request.getParameter("BOARDNO"));	// 삭제하려는 글번호
		
		// 삭제하려는 글에 답글이 하나라도 있으면 삭제 불가능
		if(biz.delCheck(BOARDNO) > 0) {
			jsResponse("답글이 존재하는 글은 삭제할 수 없습니다.", "mydetail?BOARDNO="+BOARDNO, response);
		} else {
			int res = biz.delete(BOARDNO);
			
			if(res == 1) {
				jsResponse("글 삭제 완료!", "mylist", response);
			} else {
				jsResponse("글 삭제 실패 ,,", "mydetail?BOARDNO="+BOARDNO, response);
			}
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
