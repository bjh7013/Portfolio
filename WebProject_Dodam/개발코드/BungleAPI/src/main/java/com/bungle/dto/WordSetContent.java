package com.bungle.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WordSetContent {
	private int wordsetNo;
	private int problemNo;
	private String wordsetTitle;
	private String[] problemName;
	private String problemImg;	
	private String[] wordImgData;
	
}
