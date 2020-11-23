package com.bungle.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KakaoTranslationResult {
	private String translated_text;
	private List info;
}
