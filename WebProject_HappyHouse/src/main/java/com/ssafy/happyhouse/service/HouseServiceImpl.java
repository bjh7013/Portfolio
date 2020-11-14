package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.HouseDao;
import com.ssafy.happyhouse.dto.HouseDeal;
import com.ssafy.happyhouse.dto.HouseInfo;
import com.ssafy.happyhouse.dto.housecart;
import com.ssafy.happyhouse.dto.searchPaging;
import com.ssafy.happyhouse.util.PageNavigation;

@Service
public class HouseServiceImpl implements HouseService {
	@Autowired
	private HouseDao dao;

	@Override
	public List<HouseDeal> searchAll(searchPaging searchPaging) {
		List<HouseDeal> list = dao.searchAll(searchPaging);
//		System.out.println("House Service - list :"+list);
		return list;
	}



	@Override
	public List<HouseDeal> searchDong(searchPaging searchPaging) {
		return dao.searchDong(searchPaging);
	}



	@Override
	public List<HouseDeal> searchAptname(searchPaging searchPaging) {
		return dao.searchAptname(searchPaging);
	}



	@Override
	public HouseDeal search(int no) {
		return dao.search(no);
	}


	@Override
	public PageNavigation makePageNavigation(int currentPage, int sizePerPage, int totalSize) {
		
		PageNavigation pageNavigation = new PageNavigation();
		int naviSize = 10; // 페이지 갯수
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = (totalSize - 1) / sizePerPage + 1; // 총 게시글 수
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1; // 총 페이지 수
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize; // startRange true : 이전 x false : 이전 o
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage; // endRange true : 다음x false : 다음o
		
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}



	@Override
	public int searchcount(searchPaging searchPaging) {
		// TODO Auto-generated method stub
		return dao.searchcount(searchPaging);
	}

	@Override
	public int findaptNo(housecart housecart) {
		return dao.findaptNo(housecart);
	}
	@Override
	public List<Integer> showcart(String userid){
		return dao.showcart(userid);
	}
	
	@Override
	public List<HouseInfo> searchDongformap(String dong) {
		
		return dao.searchDongformap(dong);
	}



	@Override
	public int cartcount(String userid) {
		// TODO Auto-generated method stub
		return dao.cartcount(userid);
	}

	@Override
	public List<String> distinctdong(String dong) {
		// TODO Auto-generated method stub
		return dao.distinctdong(dong);
	}

	@Override
	public boolean insertcart(housecart housecart) {
		// TODO Auto-generated method stub
		return dao.insertcart(housecart);
	} 



	@Override
	public boolean deletecart(housecart housecart) {
		// TODO Auto-generated method stub
		return dao.deletecart(housecart);
	}



	








}
