package com.bungle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpeechInfo {
	private String text;
	private String language;
	private String type;
	private int dictNo;
	private int wordTestNo;
}
