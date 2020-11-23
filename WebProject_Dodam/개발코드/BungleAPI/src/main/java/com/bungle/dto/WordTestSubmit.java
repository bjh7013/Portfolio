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
public class WordTestSubmit {
	private int submitNo; //제출 번호
	private int wordTestNo;//낱말 테스트 번호
	private int studentNo;//학생 번호
	private String submitAnswer="";//제출 답안
	private boolean isCorrect;//정답 여부
	private String submitImg;//제출답안 이미지 경로
	
	private String submitImgData;//제출답안 이미지 데이터
}
