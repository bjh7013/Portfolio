package com.ssazit.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssazit.dao.NotiDAO;
import com.ssazit.dto.Noti;

public class NotiServiceImpl implements NotiService{
	
	@Autowired
	NotiDAO notiDAO;
	
	@Override
	public boolean insertNoti(Noti noti) {
		// TODO Auto-generated method stub
		if(notiDAO.insertNoti(noti) == 1) {
			
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteNoti(Noti noti) {
		// TODO Auto-generated method stub
		if(notiDAO.deleteNoti(noti) == 1) {
			
			return true;
		}
		return false;
	}

	@Override
	public boolean selectMyNoti(Noti noti) {
		// TODO Auto-generated method stub
		return selectMyNoti(noti);
	}
}
