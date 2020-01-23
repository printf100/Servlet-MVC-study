package com.bike.biz;

import java.util.List;

import com.bike.DAO.BikeDAO;
import com.bike.DAO.BikeDAOImpl;
import com.bike.DTO.BikeDTO;

public class BikeBizImpl implements BikeBiz {

	BikeDAO dao = new BikeDAOImpl();
	
	@Override
	public int insert(List<BikeDTO> bikes) {
		return dao.insert(bikes);
	}

	@Override
	public int delete() {
		return dao.delete();
	}
}
