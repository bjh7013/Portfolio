package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.housecart;
import com.ssafy.happyhouse.dto.searchPaging;
import com.ssafy.happyhouse.util.PageNavigation;
public interface HouseService {

	public List<HouseDeal> searchAll(searchPaging searchPaging);
	public PageNavigation makePageNavigation(int pg, int sizePerPage,int totalSize) ;
	public List<HouseDeal> searchDong(searchPaging searchPaging);
	public List<HouseDeal> searchAptname(searchPaging searchPaging);
	public List<HouseInfo> searchDongformap(String dong);
	public HouseDeal search(int no);
	public int searchcount(searchPaging searchPaging);

	//cart
	public int cartcount(String userid);
	public int findaptNo(housecart housecart);
	public List<Integer> showcart(String userid);
	public List<String> distinctdong(String dong);
	public boolean insertcart(housecart housecart);
	public boolean deletecart(housecart housecart);

 
}
