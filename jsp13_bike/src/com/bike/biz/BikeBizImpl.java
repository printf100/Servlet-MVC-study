package com.bike.biz;

import com.bike.DAO.BikeDAO;
import com.bike.DAO.BikeDAOImpl;

public class BikeBizImpl implements BikeBiz {

	BikeDAO dao = new BikeDAOImpl();
	
	@Override
	public int insert(String[] dto) {
		return dao.insert(dto);
	}

}
