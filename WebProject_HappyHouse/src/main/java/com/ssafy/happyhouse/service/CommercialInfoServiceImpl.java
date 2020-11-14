package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.CommercialInfoDao;
import com.ssafy.happyhouse.dto.CommercialInfo;

@Service
public class CommercialInfoServiceImpl implements CommercialInfoService {
	@Autowired
	private CommercialInfoDao dao;

	@Override
	public List<CommercialInfo> findCommercialInfo(String dong) {
		return dao.findCommercialInfo(dong);
	}
}
