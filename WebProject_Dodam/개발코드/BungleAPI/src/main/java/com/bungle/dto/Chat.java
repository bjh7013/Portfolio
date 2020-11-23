package com.bungle.dto;

import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
	int chatNo;
	private int userNo;
	private Timestamp creationTime;
	private int chatRoomNo;
	private String content;
	private int chatType;
	private String audioPath;
	
	private String audioData;	
	
	// 조인
	private String userId;
	private String nickName;
}