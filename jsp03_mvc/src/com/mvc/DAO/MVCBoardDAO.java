package com.mvc.DAO;

import java.util.List;

import com.mvc.DTO.MVCBoardDTO;

public interface MVCBoardDAO {
	
	String SELECT_LIST_SQL = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE FROM MVCBOARD ORDER BY SEQ DESC ";
	String SELECT_ONE_SQL = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE FROM MVCBOARD WHERE SEQ = ";
	String INSERT_SQL = " INSERT INTO MVCBOARD VALUES (MVCBOARDSEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
	String UPDATE_SQL = " UPDATE MVCBOARD SET WRITER = ?, TITLE = ?, CONTENT = ? WHERE SEQ = ? ";
	String DELETE_SQL = " DELETE FROM MVCBOARD WHERE SEQ = ";

	public List<MVCBoardDTO> selectList();
	
	public MVCBoardDTO selectOne(int seq);
	
	public int insert(MVCBoardDTO dto);
	
	public int update(MVCBoardDTO dto);
	
	public int delete(int seq);
	
	public boolean multiDelete(String[] seqs);
}
