package com.bungle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bungle.dao.ChatRoomDAO;
import com.bungle.dto.ChatRoom;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

	@Autowired
	ChatRoomDAO chatRoomDao;
	
	@Override
	public List<ChatRoom> searchAllRoom() {
		return chatRoomDao.selectAllRoom();
	}

	@Override
	public ChatRoom searchRoomByClassNo(int classNo) {
		return chatRoomDao.selectRoomByClassNo(classNo);
	}

	@Override
	public int addChatRoom(ChatRoom chatRoom) {
		return chatRoomDao.insertChatRoom(chatRoom);
	}

	@Override
	public int deleteChatRoom(int chatRoomNo) {
		return chatRoomDao.deleteChatRoom(chatRoomNo);
	}

	@Override
	public List<ChatRoom> searchLimitTen(int page) {
		return chatRoomDao.selectLimitTen(page);
	}

	@Override
	public int countRows() {
		return chatRoomDao.countRows();
	}

	
	

}
