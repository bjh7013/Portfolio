package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.EnvironmentInfo;


@Mapper
public interface EnvironmentDao {
	public List<EnvironmentInfo> search(String dong) ;

}
