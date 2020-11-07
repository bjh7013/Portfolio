package com.ssazit.dao;

import java.util.List;

import com.ssazit.dto.Series;

public interface SeriesDAO {

	// C(시리즈 만들기)
	public int insertSeries(Series series);
	// U(시리즈 수정)
	public int updateSeries(Series series);
	// D(시리즈 삭제)
	public int deleteSeries(Series series);
	
	// R
	// 상세 조회
	public Series selectSeriesOne(Series series);
	// 리스트 조회
	public List<Series> selectSeriesList(Series series);
	// 리스트 갯수 조회
	public int selectSeriesCount(Series series);
	
}
