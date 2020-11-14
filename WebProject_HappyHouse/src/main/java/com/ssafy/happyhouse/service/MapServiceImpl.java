package com.ssafy.happyhouse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.MapDao;
import com.ssafy.happyhouse.dto.CommercialInfo;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.SidoCode;


@Service
public class MapServiceImpl implements MapService {
	
	@Autowired
	private MapDao dao;

	@Override
	public List<SidoCode> selectSido() {
		System.out.println("SERVICE - selectSido");
		System.out.println(dao.selectSido());
		return dao.selectSido();
	}

	@Override
	public List<SidoCode> selectGugun(String sido) {
		System.out.println("SERVICE - selectGugun");
		System.out.println(dao.selectGugun(sido));
		return dao.selectGugun(sido);
	}

	@Override
	public List<HouseInfo> selectDong(String gugun) {
		System.out.println("SERVICE - selectDong");
		System.out.println(dao.selectDong(gugun));
		return dao.selectDong(gugun);
	}

	@Override
	public List<CommercialInfo> selectCommercial(String dong) {
		System.out.println("SERVICE - selectCommercial");
		System.out.println(dao.selectCommercial(dong));
		return dao.selectCommercial(dong);
	}
	



}
