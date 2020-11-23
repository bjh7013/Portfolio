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
public class HomeworkSubmit {
	private int submitNo;
	private int homeworkNo;
	private int studentNo;
	private String submitContent;
	private String submitDate;
	private int score;
	private String evalContent;
	
	//join data
	private String sessionNo;
	private String sessionName;
	private String classNo;
	private String className;
	private String classImg;
	private String homeworkName;
	private String homeworkType;
	private String contentNo;
	
	//검색조건
	private int teacherNo;
	
}
