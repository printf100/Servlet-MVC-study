package com.bike.biz;

import java.util.List;

import com.bike.DTO.BikeDTO;

public interface BikeBiz {

	public int insert(List<BikeDTO> bikes);

	public int delete();
}
