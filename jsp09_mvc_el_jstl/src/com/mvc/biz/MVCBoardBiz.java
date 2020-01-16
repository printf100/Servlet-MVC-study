package com.mvc.biz;

import java.util.List;

import com.mvc.DTO.MVCBoardDTO;

public interface MVCBoardBiz {

	public List<MVCBoardDTO> selectList();
	
	public MVCBoardDTO selectOne(int seq);
	
	public int insert(MVCBoardDTO dto);
	
	public int update(MVCBoardDTO dto);
	
	public int delete(int seq);
	
	public boolean multiDelete(String[] seqs);
}
