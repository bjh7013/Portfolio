package com.bungle.service;

import java.util.List;

import com.bungle.dto.Chat;
import com.bungle.dto.User;

public interface ChatService {
	int addChat(Chat chat);
	int deleteChat(int chatNo);
	List<Chat> searchChatByRoomNo(Chat chat);
	List<Chat> searchLimitTen(int page);
	public int countRows();
	public boolean isAuthorized(Chat chat);
	public String getChatAudioFile(Chat chat);
	List<Chat> selectChatBefore(Chat chat);

}
