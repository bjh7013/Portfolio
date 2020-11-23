package com.bungle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bungle.dto.Chat;

@Mapper
public interface ChatDAO {
	public int insertChat(Chat chat);
	public int deleteChat(int chatNo);
	public List<Chat> selectChatByRoomNo(Chat chat);
	public List<Chat> selectLimitTen(int page);
	public int countRows();
	public int updateChat(Chat chat);
	public boolean isAuthorized(Chat chat);
	public String getChatAudioFile(Chat chat);

	public List<Chat> selectChatBefore(Chat chat);
}
