package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.RsinfoDao;
import com.ssafy.happyhouse.dto.rsInfo;

@Service
public class rsInfoServiceImpl implements rsInfoService{
	@Autowired
	private RsinfoDao dao;
	@Override
	public List<rsInfo> searchAll(String dong) {
		System.out.println("service:"+dao.searchAll(dong));
		return dao.searchAll(dong);
	}

}
