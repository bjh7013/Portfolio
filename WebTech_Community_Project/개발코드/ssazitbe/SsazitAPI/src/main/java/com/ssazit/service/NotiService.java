package com.ssazit.service;

import com.ssazit.dto.Noti;

public interface NotiService {
	
	public boolean insertNoti(Noti noti);
	public boolean deleteNoti(Noti noti);
	public boolean selectMyNoti(Noti noti);
}
