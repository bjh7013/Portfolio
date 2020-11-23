package com.bungle.dao;

import java.util.List;

import com.bungle.dto.HomeworkSubmit;

public interface HomeworkSubmitDAO {
	public int createHomeworkSubmit(HomeworkSubmit homeworkSubmit);
	public int updateHomeworkSubmit(HomeworkSubmit homeworkSubmit);
	public int deleteHomeworkSubmit(HomeworkSubmit homeworkSubmit);
	
	public List<HomeworkSubmit> searchHomeworkSubmitTeacher(HomeworkSubmit searchCondition);
	public List<HomeworkSubmit> searchHomeworkSubmitStudent(HomeworkSubmit searchCondition);
	
	public HomeworkSubmit getHomeworkSubmit(HomeworkSubmit searchCondition);
	
	public boolean validateC(HomeworkSubmit homeworkSubmit);
	public boolean validateUD(HomeworkSubmit homeworkSubmit);
	public boolean isAlreadySubmitted(HomeworkSubmit homeworkSubmit);
	//과제 평가
	public int homeworkEval(HomeworkSubmit homeworkSubmit);
	public int deleteHomeworkEval(HomeworkSubmit homeworkSubmit);
	public boolean validateEval(HomeworkSubmit homeworkSubmit);

	
//	public HomeworkEval getHomeworkEval(HomeworkEval searchCondition);
	
//	public boolean validateC(HomeworkEval homeworkEval);
//	public boolean validateUD(HomeworkEval homeworkEval);

//	public List<HomeworkEval> searchHomeworkEvalTeacher(HomeworkEval searchCondition);
//	public List<HomeworkEval> searchHomeworkEvalStudent(HomeworkEval searchCondition);
	
	
}
