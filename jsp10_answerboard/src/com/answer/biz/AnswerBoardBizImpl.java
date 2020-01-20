package com.answer.biz;

import java.util.List;

import com.answer.DAO.AnswerBoardDAO;
import com.answer.DAO.AnswerBoardDAOImpl;
import com.answer.DTO.AnswerBoardDTO;

public class AnswerBoardBizImpl implements AnswerBoardBiz {

	AnswerBoardDAO dao = new AnswerBoardDAOImpl();
	
	@Override
	public List<AnswerBoardDTO> selectList() {
		return dao.selectList();
	}

	@Override
	public AnswerBoardDTO selectOne(int boardNo) {
		return dao.selectOne(boardNo);
	}

	@Override
	public int insert(AnswerBoardDTO dto) {
		return dao.insert(dto);
	}
	
	@Override
	public boolean updateOrder(int boardNo) {
		if(dao.updateOrder(boardNo))
			return true;
		else
			return false;
	}
	
	@Override
	public int insertAnswer(AnswerBoardDTO dto) {
		return dao.insertAnswer(dto);
	}

	@Override
	public int update(AnswerBoardDTO dto) {
		return dao.update(dto);
	}

	@Override
	public int delCheck(int boardNo) {
		return dao.delCheck(boardNo);
	}

	@Override
	public int delete(int boardNo) {
		dao.delUpdateOrder(boardNo); // 삭제한 글보다 높은 GROUPORDER를 -1
		
		int res = 0;
		
		if(dao.delete(boardNo) > 0) {
			res = 1;
		} else {
			res = -1;
		}
		
		return res;
	}

}
