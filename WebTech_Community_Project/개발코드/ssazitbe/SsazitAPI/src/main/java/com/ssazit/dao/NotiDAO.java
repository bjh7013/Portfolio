package com.ssazit.dao;

import java.util.List;

import com.ssazit.dto.Noti;

public interface NotiDAO {
	public int insertNoti(Noti noti);
	public int deleteNoti(Noti noti);
	public List<Noti> selectMyNoti(Noti noti);
}
