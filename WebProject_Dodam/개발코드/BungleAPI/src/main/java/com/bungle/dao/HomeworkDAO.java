package com.bungle.dao;

import java.util.List;

import com.bungle.dto.Homework;

public interface HomeworkDAO {
	public int createHomework(Homework homework);
	public int updateHomework(Homework homework);
	public int deleteHomework(Homework homework);
	
	public List<Homework> searchHomeworkTeacher(Homework searchCondition);
	public List<Homework> searchHomeworkStudent(Homework searchCondition);
	public List<Homework> searchHomeworkClass(Homework searchCondition);

	
	public Homework getHomework(Homework searchCondition);
	
	public boolean validateC(Homework homework);
	public boolean validateUD(Homework homework);
}