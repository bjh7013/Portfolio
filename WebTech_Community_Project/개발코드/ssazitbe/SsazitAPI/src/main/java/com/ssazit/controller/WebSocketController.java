package com.ssazit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.ssazit.dto.Message;

@Controller
public class WebSocketController {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@MessageMapping("/sendmsg/{userNo}")
	public void sendMsg(@DestinationVariable String userNo,Message message) {
		System.out.println(message);
		System.out.println("@#)#*@()#*@)(_#*)@(_*#)_@*#)_(@*#)_@(*#()_@*#)_*@)_#*@)_#*)_#*@()_");
		System.out.println("@#)#*@()#*@)(_#*)@(_*#)_@*#)_(@*#)_@(*#()_@*#)_*@)_#*@)_#*)_#*@()_");
		System.out.println("@#)#*@()#*@)(_#*)@(_*#)_@*#)_(@*#)_@(*#()_@*#)_*@)_#*@)_#*)_#*@()_");
		simpMessagingTemplate.convertAndSend("/topic/message/" + userNo, message);
		
	}
}
