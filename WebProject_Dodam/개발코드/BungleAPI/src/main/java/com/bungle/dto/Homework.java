package com.bungle.dto;

import java.util.List;

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
@NoArgsConstructor
@AllArgsConstructor
public class Homework {
	private int homeworkNo;
	private String homeworkName;
	private String homeworkType;
	private String homeworkContent;
	private int sessionNo;
	private int contentNo;
	private String dueDate;
	
	//join data
	private String sessionName;
	private int classNo;
	private int userNo; //해당 과제가 등록된 클래스를 개설한 교사의 번호
	private int studentNo; //해당 과제가 등록된 클래스를 수강중인 학생의 번호
	private int teacherNo;
	
	private List<Dictation> dictationList;
	private List<WordTest> wordTestList;
	
	private WordProblem[] problems;
	private String[] wordImgData;
	private int[] checkData;
	
}
