package com.bungle.dto;

public class ClassTime {
	private int timeNo;
	private int classDay;
	private String startTime;
	private String endTime;
	private int classNo;
	private String timeName;
	private int userNo;
	@Override
	public String toString() {
		return "ClassTime [timeNo=" + timeNo + ", classDay=" + classDay + ", startTime=" + startTime + ", endTime="
				+ endTime + ", classNo=" + classNo + ", timeName=" + timeName + ", userNo=" + userNo + ", checkOwner="
				+ checkOwner + "]";
	}

	private boolean checkOwner;

	public ClassTime() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClassTime(int timeNo, int classDay, String startTime, String endTime, int classNo, String timeName,
			int userNo, boolean checkOwner) {
		super();
		this.timeNo = timeNo;
		this.classDay = classDay;
		this.startTime = startTime;
		this.endTime = endTime;
		this.classNo = classNo;
		this.timeName = timeName;
		this.userNo = userNo;
		this.checkOwner = checkOwner;
	}

	public int getTimeNo() {
		return timeNo;
	}

	public void setTimeNo(int timeNo) {
		this.timeNo = timeNo;
	}

	public int getClassDay() {
		return classDay;
	}

	public void setClassDay(int classDay) {
		this.classDay = classDay;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getClassNo() {
		return classNo;
	}

	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}

	public String getTimeName() {
		return timeName;
	}

	public void setTimeName(String timeName) {
		this.timeName = timeName;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public boolean isCheckOwner() {
		return checkOwner;
	}

	public void setCheckOwner(boolean checkOwner) {
		this.checkOwner = checkOwner;
	}

}
