package com.bungle.service;

import java.util.List;

import com.bungle.dto.ChatRoom;


public interface ChatRoomService {
	public List<ChatRoom> searchAllRoom();
	public ChatRoom searchRoomByClassNo(int classNo);
	public int addChatRoom(ChatRoom chatRoom);
	public int deleteChatRoom(int chatRoomNo);
	public List<ChatRoom> searchLimitTen(int page);
	public int countRows();
}
