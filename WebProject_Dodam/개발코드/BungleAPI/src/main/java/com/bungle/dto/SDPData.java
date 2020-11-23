package com.bungle.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SDPData {
	private String sdpMid;
	private String candidate;
	private String offer;
	private String answer;
	private String sdp;
	private String type;
}
