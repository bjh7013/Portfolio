package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.EnvironmentDao;
import com.ssafy.happyhouse.dto.EnvironmentInfo;

@Service
public class EnvironmentServiceImpl implements EnvironmentService {

	@Autowired
	private EnvironmentDao dao;

	@Override
	public List<EnvironmentInfo> search(String dong) {
		System.out.println("EnvironmentServiceImpl");
		return dao.search(dong);

	}

}
