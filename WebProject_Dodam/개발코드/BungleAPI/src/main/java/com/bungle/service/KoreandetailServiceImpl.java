package com.bungle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bungle.dao.KoreanDetailDAO;
import com.bungle.dto.Koreandetail;

@Service
public class KoreandetailServiceImpl implements KoreandetailService {
	
	@Autowired
	KoreanDetailDAO koreanDetailDAO;
	
	@Override
	public boolean createKoreandetail(Koreandetail koreandetail) {
		return koreanDetailDAO.createKoreandetail(koreandetail);
	}

	@Override
	public boolean updateKoreandetail(Koreandetail koreandetail) {
		return koreanDetailDAO.updateKoreandetail(koreandetail);
	}

	@Override
	public boolean deleteKoreandetail(Koreandetail koreandetail) {
		return koreanDetailDAO.deletekoreandetail(koreandetail);
	}

	@Override
	public List<Koreandetail> selectUserCorrect(int studentNo) {
		return koreanDetailDAO.selectUserCorrect(studentNo);
	}

	@Override
	public Koreandetail selectOneKoreandetail(Koreandetail koreandetail) {
		return koreanDetailDAO.selectOneKoreandetail(koreandetail);
	}

	@Override
	public int selectUserCorrectCount(int studentNo) {
		return koreanDetailDAO.selectUserCorrectCount(studentNo);
	}


}
