package com.answer.DAO;

import java.util.List;

import com.answer.DTO.AnswerBoardDTO;

public interface AnswerBoardDAO {

	public List<AnswerBoardDTO> selectList();
	
	public AnswerBoardDTO selectOne(int boardNo);
	
	// 일반 글 작성
	public int insert(AnswerBoardDTO dto);
	
	// 답글 작성 전에 부모글과 그룹번호가 같고 부모 그룹순서보다 더 큰 글들의 그룹순서를 +1 해주기
	public boolean updateOrder(int boardNo);
	
	// 답글 작성
	public int insertAnswer(AnswerBoardDTO dto);
	
	// 글 수정
	public int update(AnswerBoardDTO dto);
	
	// 글 삭제 전에 답글이 있는지 체크
	public int delCheck(int boardNo);
	
	// 글 삭제
	public int delete(int boardNo);
	
	// 글 삭제 후에 같은 GROUPNO 안에서 지운 글보다 GROUPORDER가 더 큰 글들의 GROUPORDER를 -1 해주기
	public void delUpdateOrder(int boardNo);
}
