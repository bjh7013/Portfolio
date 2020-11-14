package com.ssafy.happyhouse.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.housecart;
import com.ssafy.happyhouse.dto.searchPaging;

@Mapper
public interface HouseDao {
	//house
	public List<HouseDeal> searchAll(searchPaging searchPaging) ;
	public List<HouseDeal> searchDong(searchPaging searchPaging) ;
	public List<HouseDeal> searchAptname(searchPaging searchPaging) ;
	public HouseDeal search(int no)  ;
	public int searchcount(searchPaging searchPaging);
	
	//map
	public List<HouseInfo> searchDongformap(String dong);
	
	//cart
	public int cartcount(String userid);
	public int findaptNo(housecart housecart);
	public List<Integer> showcart(String userid);
	public List<String> distinctdong(String dong);
	public boolean insertcart(housecart housecart);
	public boolean deletecart(housecart housecart);
	
} 
