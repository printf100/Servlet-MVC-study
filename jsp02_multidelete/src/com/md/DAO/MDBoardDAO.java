package com.md.DAO;

import java.util.List;

import com.md.DTO.MDBoardDTO;

public interface MDBoardDAO {

	public List<MDBoardDTO> selectList();
	
	public MDBoardDTO selectOne(int seq);	
	
	public int insert(MDBoardDTO dto);
	
	public int update(MDBoardDTO dto);

	public int delete(int seq);
	
	public int multiDelete(String[] chks);
}
