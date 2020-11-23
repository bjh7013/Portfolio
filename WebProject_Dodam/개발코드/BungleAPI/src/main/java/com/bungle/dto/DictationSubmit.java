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
public class DictationSubmit {
	private int submitNo; // 제출번호
	private int dictationNo; // 받아쓰기번호
	private int studentNo; // 학생 번호
	private String submitAnswer; // 학생 답
	private boolean isCorrect; // 정답여부
	private String submitImg;// 받아쓰기
	
	private String submitImgData;
	private String submitOCR;
}
