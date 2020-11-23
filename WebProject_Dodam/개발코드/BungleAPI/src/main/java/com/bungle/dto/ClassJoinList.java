package com.bungle.dto;

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
public class ClassJoinList {
	private int classNo;
	private int studentNo;
	private String userId;
	private String userName;
	private Date birthDate;
	private String profileImg;
}
