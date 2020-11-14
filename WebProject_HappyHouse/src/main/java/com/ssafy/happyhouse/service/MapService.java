package com.ssafy.happyhouse.service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.dto.CommercialInfo;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.SidoCode;



public interface MapService {

	List<SidoCode> selectSido();

	List<SidoCode> selectGugun(String sido);

	List<HouseInfo> selectDong(String gugun);

	List<CommercialInfo> selectCommercial(String dong);
}
