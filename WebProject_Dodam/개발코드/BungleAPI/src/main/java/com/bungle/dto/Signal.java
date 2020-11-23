package com.bungle.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Signal {
	private String type;
	private String userNo;
	private SDPData data;
	private String toUser;
	private String classNo;
	
}
