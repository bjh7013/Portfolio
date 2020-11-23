package com.bungle.service;

import java.util.List;

import com.bungle.dto.Homework;
import com.bungle.dto.HomeworkSubmit;
import com.bungle.dto.User;

public interface HomeworkService {
	//homework
	public boolean createHomework(Homework homework, User loginUser);
	public boolean updateHomework(Homework homework, User loginUser);
	public boolean deleteHomework(Homework homework, User loginUser);
	
	public List<Homework> searchHomeworkTeacher(Homework searchCondition, User loginUser);
	public List<Homework> searchHomeworkStudent(Homework searchCondition, User loginUser);
	public List<Homework> searchHomeworkClass(Homework searchCondition);

	public Homework getHomework(Homework searchCondition, User loginUser);
	
	//homeworkEval
	//과제 평가
	public boolean homeworkEval(HomeworkSubmit homeworkSubmit, User loginUser);
	public boolean deleteHomeworkEval(HomeworkSubmit homeworkSubmit, User loginUser);
	
	//homeworkSubmit
	public boolean createHomeworkSubmit(HomeworkSubmit homeworkSubmit, User loginUser);
	public boolean createHomeworkSubmitToWord(HomeworkSubmit homeworkSubmit, User loginUser);

	public boolean updateHomeworkSubmit(HomeworkSubmit homeworkSubmit, User loginUser);
	public boolean deleteHomeworkSubmit(HomeworkSubmit homeworkSubmit, User loginUser);
	
	public List<HomeworkSubmit> searchHomeworkSubmitTeacher(HomeworkSubmit searchCondition, User loginUser);
	public List<HomeworkSubmit> searchHomeworkSubmitStudent(HomeworkSubmit searchCondition, User loginUser);
	
	public HomeworkSubmit getHomeworkSubmit(HomeworkSubmit searchCondition, User loginUser);
}
