package com.bike.DAO;

import java.util.List;

import com.bike.DTO.BikeDTO;

public interface BikeDAO {

	public int insert(List<BikeDTO> bikes);
	
	public int delete();
}
