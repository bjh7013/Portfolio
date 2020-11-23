package com.bungle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bungle.dto.ChatRoom;

@Mapper
public interface ChatRoomDAO {
	public List<ChatRoom> selectAllRoom();
	public ChatRoom selectRoomByClassNo(int classNo);
	public int insertChatRoom(ChatRoom chatRoom);
	public int deleteChatRoom(int chatRoomNo);
	public List<ChatRoom> selectLimitTen(int page);
	public int countRows();
}
