package com.bungle.dto;

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
public class User {

	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private String nickName;
	private String userType;
	private String birthDate;
	private String profileImg;
	private String email;
	
}
