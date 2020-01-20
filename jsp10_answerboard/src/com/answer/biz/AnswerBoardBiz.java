package com.answer.biz;

import java.util.List;

import com.answer.DTO.AnswerBoardDTO;

public interface AnswerBoardBiz {

public List<AnswerBoardDTO> selectList();
	
	public AnswerBoardDTO selectOne(int boardNo);
	
	public int insert(AnswerBoardDTO dto);
	
	// 답글 작성 전에 부모글과 그룹번호가 같고 부모 그룹순서보다 더 큰 글들의 그룹순서를 +1 해주기
	public boolean updateOrder(int boardNo);
		
	// 답글 작성
	public int insertAnswer(AnswerBoardDTO dto);
	
	public int update(AnswerBoardDTO dto);
	
	// 글 삭제 전에 답글이 있는지 체크
	public int delCheck(int boardNo);
	
	public int delete(int boardNo);
	
	//public int delUpdateOrder(int boardNo);
}
