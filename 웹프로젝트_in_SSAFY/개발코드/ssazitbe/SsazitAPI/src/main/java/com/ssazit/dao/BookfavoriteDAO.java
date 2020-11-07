package com.ssazit.dao;

import java.util.List;
import com.ssazit.dto.Bookfavorite;

public interface BookfavoriteDAO {

	// C(책 즐겨찾기 만들기)
	public int insertBookfavorite(Bookfavorite bookfavorite);
	// U(책 즐겨찾기 수정)
	public int updateBookfavorite(Bookfavorite bookfavorite);
	// D(책 즐겨찾기 삭제)
	public int deleteBookfavorite(Bookfavorite bookfavorite);
	
	// R
	// 상세 조회
	public Bookfavorite selectBookfavoriteOne(Bookfavorite bookfavorite);
	// 리스트 조회
	public List<Bookfavorite> selectBookfavoriteList(Bookfavorite bookfavorite);
	// 리스트 갯수 조회
	public int selectBookfavoriteCount(Bookfavorite bookfavorite);
	
}
