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
@AllArgsConstructor
@NoArgsConstructor
public class DictationSet {
	private int dictationsetNo;
	private String dictationsetName;
	private int userNo;
	private List<DictationSetContent> dictationSetContentList;
	
}
