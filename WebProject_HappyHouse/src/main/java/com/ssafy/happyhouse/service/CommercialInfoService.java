package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.CommercialInfo;



public interface CommercialInfoService {
	public List<CommercialInfo> findCommercialInfo(String dong);
}