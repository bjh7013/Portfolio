package com.bungle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bungle.dao.ChatDAO;
import com.bungle.dto.Audio;
import com.bungle.dto.Chat;

@Service
public class ChatServiceImpl  implements ChatService{
	@Autowired
	ChatDAO chatDao;
	@Autowired
	VoiceService voiceService;
	
	@Override
	public int addChat(Chat chat) {
		System.out.println(chat.toString());
		// 1 - 입장 / 2 - 텍스트 / 3 - 오디오
		if(chatDao.insertChat(chat) != 1)
			return 0;
		
		if(chat.getChatType() == 3) {
			Audio audio = new Audio();
			audio.setAudioData(chat.getAudioData());
			
			chat.setAudioPath(chat.getChatNo() + ".mp3");
			
			audio.setFileName(chat.getAudioPath());
		
			if(!voiceService.saveChatAudio(audio)) {
				System.out.println("파일 입출력 에러");
			}else {
				chatDao.updateChat(chat);			
			}
			
		}
		return 1;
	}

	@Override
	public int deleteChat(int chatNo) {
		return chatDao.deleteChat(chatNo);
	}

	@Override
	public List<Chat> searchChatByRoomNo(Chat chat) {
		return chatDao.selectChatByRoomNo(chat);
	}

	@Override
	public List<Chat> searchLimitTen(int page) {
		return chatDao.selectLimitTen(page);
	}

	@Override
	public int countRows() {
		return chatDao.countRows();
	}

	@Override
	public boolean isAuthorized(Chat chat) {
		// TODO Auto-generated method stub
		
		return chatDao.isAuthorized(chat);
	}

	@Override
	public String getChatAudioFile(Chat chat) {
		// TODO Auto-generated method stub
		return chatDao.getChatAudioFile(chat);
	}
	
	public List<Chat> selectChatBefore(Chat chat) {
		return chatDao.selectChatBefore(chat);
	}

	

}
