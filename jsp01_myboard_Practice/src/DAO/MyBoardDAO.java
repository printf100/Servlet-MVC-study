package DAO;

import java.util.List;

import DTO.MyBoardDTO;

public interface MyBoardDAO {

	public List<MyBoardDTO> selectList();
	
	public MyBoardDTO selectOne(int myNo);
	
	public int insert(MyBoardDTO dto);
	
	public int update(MyBoardDTO dto);
	
	public int delete(int myNo);
}
