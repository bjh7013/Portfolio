package com.bungle.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bungle.dto.Chat;
import com.bungle.dto.ChatRoom;
import com.bungle.service.ChatRoomService;
import com.bungle.service.ChatService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Controller
@RequestMapping("/chatroom")
public class ChatRoomController {
	
	@Autowired
	ChatRoomService chatRoomService;
	
	@Autowired
	ChatService chatService;
	
	@GetMapping("/class")
	public ResponseEntity<ChatRoom> ClassRoom(@RequestParam int classNo) {
		ChatRoom room=null;
		System.out.println("대화 생성");
			if(chatRoomService.searchRoomByClassNo(classNo)==null) {
				System.out.println("채팅방 생성");
				chatRoomService.addChatRoom(new ChatRoom(classNo,classNo));
				System.out.println(chatRoomService.searchRoomByClassNo(classNo).toString());
			}
			room=chatRoomService.searchRoomByClassNo(classNo);
	
		 return new ResponseEntity<ChatRoom>(room, HttpStatus.OK);
	}
	
	@PostMapping("/createroom")
	public ResponseEntity<String> CreateRoom(@RequestBody ChatRoom chatRoom) {
		String result=null;
		try {
		
			chatRoomService.addChatRoom(chatRoom);
			result="success";
		} catch (Exception e) {
			e.printStackTrace();
			result="fail";
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@PostMapping("/chatlog")
	public ResponseEntity<List<Chat>> chatLog(@RequestBody Chat chat) {
		List<Chat> result=null;
		System.out.println(chat.toString());
		try {
		
			result=chatService.selectChatBefore(chat);
			
			for(int i=0;i<result.size();i++) {
				System.out.println(result.get(i));
			}
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

		}
	 }
	
}
