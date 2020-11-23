package com.bungle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Clazz {

	private int classNo; // 클래스번호
	private String className; // 클래스이름
	private String classDesc; // 클래스설명
	private String targetAge; // 대상연령
	private int userLimit; // 정원
	private String openType; // 공개유형
	private String startDate; // 클래스 시작일
	private String endDate; // 클래스 종료일
	private String classImg; // 클래스 이미지
	private int userNo; // 개설자 번호
	
	private String teacherName;
	private int approved;
	private int studentCnt;
	private boolean checkOwner;
}
