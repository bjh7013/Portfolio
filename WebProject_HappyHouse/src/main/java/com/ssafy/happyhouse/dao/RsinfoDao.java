package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.rsInfo;

@Mapper
public interface RsinfoDao {
	public List<rsInfo> searchAll(String dong);
}
