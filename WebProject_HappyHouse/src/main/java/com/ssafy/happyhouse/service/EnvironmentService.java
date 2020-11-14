package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.EnvironmentInfo;


public interface EnvironmentService {

	public List<EnvironmentInfo> search(String dong);
}
