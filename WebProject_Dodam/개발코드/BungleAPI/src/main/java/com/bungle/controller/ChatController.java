package com.bungle.controller;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bungle.dto.Chat;
import com.bungle.dto.User;
import com.bungle.service.ChatService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Controller
public class ChatController {
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@Autowired
	ChatService chatService;
	@Value("${file.path.chat_audio}")
	String chatAudioFilePath;
	
	@GetMapping(value = "/chataudio/{chatNo}", produces ="audio/mpeg")
	public @ResponseBody ResponseEntity<byte[]> getAudioWithMediaType(@PathVariable int chatNo, HttpServletRequest request) throws IOException {
		int userNo = ((User)request.getAttribute("userinfo")).getUserNo();
		Chat chat = Chat.builder().chatNo(chatNo).userNo(userNo).build();
		
		System.out.println("ffffffffffffffffffffffffffffffffffffffffffffffffffff");
		System.out.println(chat);
		if(!chatService.isAuthorized(chat))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		
		String path = chatService.getChatAudioFile(chat);
		if(path == null || path.trim().equals("")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
			
		FileInputStream fis = new FileInputStream(chatAudioFilePath + "/" + path);
		 
		return new ResponseEntity<>(IOUtils.toByteArray(fis),HttpStatus.OK);
	}

	@MessageMapping("/chat/message")
	public void message(Chat chat) {
		System.out.println("채팅 들어옴");
		if(chat.getChatType()==1 || chat.getChatType() == 0) {
			chat.setContent(chat.getNickName()+"("+chat.getUserId()+")"+"님이 입장하셨습니다.");
//			chatService.addChat(chat);
		} else {
			chatService.addChat(chat);
			
		}
		
       messagingTemplate.convertAndSend("/sub/chatroom/class?classNo="+chat.getChatRoomNo(),chat);

	}
}
