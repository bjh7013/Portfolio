package com.bungle.dto;

import java.sql.Timestamp;

import com.google.auto.value.AutoValue.Builder;

import lombok.AllArgsConstructor;
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
public class ChatRoom {
	private int classNo;
	private int chatRoomNo;
	private Timestamp creationTime;
	public ChatRoom(int classNo,int chatRoomNo){
		this.classNo=classNo;
		this.chatRoomNo=chatRoomNo;
	}
}