package com.bungle.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Dictation {
	private int dictationNo; // 받아쓰기 번호
	private String dictationSentence; // 받아쓰기 문장
	private int homeworkNo; // 숙제번호
	private int dictationLevel; // 숙제난이도
	private String audioPath; //오디오 파일 경로
	private String audioString;
	
	private int teacherNo;
	
	public Dictation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dictation(int dictationNo, String dictationSentence, int homeworkNo, int dictationLevel) {
		super();
		this.dictationNo = dictationNo;
		this.dictationSentence = dictationSentence;
		this.homeworkNo = homeworkNo;
		this.dictationLevel = dictationLevel;
	}

}
