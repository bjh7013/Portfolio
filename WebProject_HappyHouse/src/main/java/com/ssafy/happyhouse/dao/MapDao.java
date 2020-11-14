package com.ssafy.happyhouse.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.CommercialInfo;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.SidoCode;

@Mapper
public interface MapDao {
	List<SidoCode> selectSido();
	List<SidoCode> selectGugun(String sido);
	List<HouseInfo> selectDong(String gugun);
	List<CommercialInfo> selectCommercial(String dong);
}
