package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.CommercialInfo;

@Mapper
public interface CommercialInfoDao {
	public List<CommercialInfo> findCommercialInfo(String dong);
}
