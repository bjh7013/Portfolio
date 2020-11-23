package com.bungle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bungle.dto.Signal;

@RestController
public class WebRTCController {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@MessageMapping("/establish")
	public void webRtc(@RequestBody Signal msg){
		simpMessagingTemplate.convertAndSend("/sub/webrtc/" + msg.getClassNo(), msg);
		System.out.println(msg);
		System.out.println("===================================");
	}
}
