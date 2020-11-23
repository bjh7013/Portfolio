package com.bungle.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

public class PointData {
	private String imgWidth;
	private String imgHeight;
	private String path;
	private String fileName;
	private boolean isAni;
	
	
	@Override
	public String toString() {
		return "{\"imgWidth\" : " + imgWidth + ", \"imgHeight\" :" + imgHeight + ", \"path\" :" + path + "}";
	}
}
