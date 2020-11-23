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
public class Koreandetail {
	private int koreandetailNo;
	private boolean isCorrect;
	private int studentNo;
	private int submitNo;
}
