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
public class WordTest {

	private int testNo; //낱말 테스트 번호
	private String word; // 낱말
	private String wordImg; // 낱말 이미지 경로
	private int homeworkNo; // 할당된 과제 번호
	private int categoryNo; // 할당된 카테고리 번호
}
