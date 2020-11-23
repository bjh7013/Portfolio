package com.bungle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DictationSetContent {
	private int dictationsetNo;
	private int dictationNumber;
	private String dictationProblem;
	private String audioPath;
	private String audioData;
}
