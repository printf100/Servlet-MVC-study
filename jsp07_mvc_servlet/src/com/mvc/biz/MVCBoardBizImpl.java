package com.mvc.biz;

import java.util.List;

import com.mvc.DAO.MVCBoardDAO;
import com.mvc.DAO.MVCBoardDAOImpl;
import com.mvc.DTO.MVCBoardDTO;

public class MVCBoardBizImpl implements MVCBoardBiz {
	
	private MVCBoardDAO dao = new MVCBoardDAOImpl();

	@Override
	public List<MVCBoardDTO> selectList() {
		
		return dao.selectList();
	}

	@Override
	public MVCBoardDTO selectOne(int seq) {
		
		return dao.selectOne(seq);
	}

	@Override
	public int insert(MVCBoardDTO dto) {
		
		return dao.insert(dto);
	}

	@Override
	public int update(MVCBoardDTO dto) {
		
		return dao.update(dto);
	}

	@Override
	public int delete(int seq) {
		
		return dao.delete(seq);
	}

	@Override
	public boolean multiDelete(String[] seqs) {
		
		return dao.multiDelete(seqs);
	}

}
