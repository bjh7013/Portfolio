package com.bungle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bungle.dao.HomeworkDAO;
import com.bungle.dao.HomeworkSubmitDAO;
import com.bungle.dto.Homework;
import com.bungle.dto.HomeworkSubmit;
import com.bungle.dto.User;

@Service
public class HomeworkServiceImpl implements HomeworkService {
	@Autowired
	HomeworkDAO homeworkDAO;
	@Autowired
	HomeworkSubmitDAO homeworkSubmitDAO;
	
	@Override
	public boolean createHomework(Homework homework, User loginUser) {
		// TODO Auto-generated method stub
		homework.setTeacherNo(loginUser.getUserNo());
		if(!homeworkDAO.validateC(homework)) {
			System.out.println("해당 session에 과제를 생성할 권한이 없음");
			return false;
		}
		
		if(homeworkDAO.createHomework(homework) != 1) {
			return false;
		}
		
		return true;
	}
	@Override
	public boolean updateHomework(Homework homework, User loginUser) {
		// TODO Auto-generated method stub
		homework.setTeacherNo(loginUser.getUserNo());
		if(!homeworkDAO.validateUD(homework)) {
			System.out.println("해당 session에 과제를 수정/삭제할 권한이 없음");
			return false;
		}
		
		if(homeworkDAO.updateHomework(homework) != 1) {
			return false;
		}
		
		return true;
	}
	@Override
	public boolean deleteHomework(Homework homework, User loginUser) {
		// TODO Auto-generated method stub
		homework.setTeacherNo(loginUser.getUserNo());
		if(!homeworkDAO.validateUD(homework)) {
			System.out.println("해당 session에 과제를 수정/삭제할 권한이 없음");
			return false;
		}
		
		if(homeworkDAO.deleteHomework(homework) != 1) {
			return false;
		}
		return true;
	}
	@Override
	public List<Homework> searchHomeworkTeacher(Homework searchCondition, User loginUser) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Homework> searchHomeworkStudent(Homework searchCondition, User loginUser) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Homework getHomework(Homework searchCondition, User loginUser) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * homework_submit
	 */
	@Override
	public boolean createHomeworkSubmit(HomeworkSubmit homeworkSubmit, User loginUser) {
		// TODO Auto-generated method stub
		
		//권한 검사 - 해당 학생이 homework가 등록된 클래스에 소속되어있는지 검사 and 제출기한이 지나지 않았는지
		homeworkSubmit.setStudentNo(loginUser.getUserNo());
		
		if(!homeworkSubmitDAO.validateC(homeworkSubmit)) {
			System.out.println("과제 제출 권한이 없음");
			return false;
		}
		if(homeworkSubmitDAO.isAlreadySubmitted(homeworkSubmit)) {
			System.out.println("이미 제출한 과제");
			return false;
		}
		
		if(homeworkSubmitDAO.createHomeworkSubmit(homeworkSubmit) != 1) {
			return false;
		}
		
		return true;
	}
	@Override
	public boolean updateHomeworkSubmit(HomeworkSubmit homeworkSubmit, User loginUser) {
		// TODO Auto-generated method stub
		//권한 검사 - 해당 학생이 수정하려는 homework를 제출한 당사자인지 and 과제 제출 기한이 지나지 않았는지 
		homeworkSubmit.setStudentNo(loginUser.getUserNo());
		
		if(!homeworkSubmitDAO.validateUD(homeworkSubmit)) {
			System.out.println("과제 수정 권한이 없음");
			return false;
		}

		
		if(homeworkSubmitDAO.updateHomeworkSubmit(homeworkSubmit) != 1) {
			return false;
		}
		
		return true;		
	}
	@Override
	public boolean deleteHomeworkSubmit(HomeworkSubmit homeworkSubmit, User loginUser) {
		// TODO Auto-generated method stub
		/**
			자신이 제출한 과제이고, 마감기한이 지나지 않았을 때만 삭제 가능
		 */
		homeworkSubmit.setStudentNo(loginUser.getUserNo());
		if(!homeworkSubmitDAO.validateUD(homeworkSubmit)) {
			System.out.println("과제 삭제 권한이 없음");
			return false;
		}
		
		if(homeworkSubmitDAO.deleteHomeworkSubmit(homeworkSubmit) != 1) {
			return false;
		}
		
		return true;		
	}
	
	@Override
	public List<HomeworkSubmit> searchHomeworkSubmitTeacher(HomeworkSubmit searchCondition, User loginUser) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<HomeworkSubmit> searchHomeworkSubmitStudent(HomeworkSubmit searchCondition, User loginUser) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HomeworkSubmit getHomeworkSubmit(HomeworkSubmit searchCondition, User loginUser) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 과제 평가
	 */
	@Override
	public boolean homeworkEval(HomeworkSubmit homeworkSubmit, User loginUser) {
		// TODO Auto-generated method stub
		//제출한 과제가 등록된 클래스를 개설한 유저일 때만 평가 가능
		homeworkSubmit.setTeacherNo(loginUser.getUserNo());
		
		if(!homeworkSubmitDAO.validateEval(homeworkSubmit)) {
			System.out.println("과제 평가 권한이 없음");
			return false;
		}
		
		if(homeworkSubmitDAO.homeworkEval(homeworkSubmit) != 1) {
			return false;
		}
			
		return true;
	}
	@Override
	public boolean deleteHomeworkEval(HomeworkSubmit homeworkSubmit, User loginUser) {
		// TODO Auto-generated method stub
		homeworkSubmit.setTeacherNo(loginUser.getUserNo());
		
		if(!homeworkSubmitDAO.validateEval(homeworkSubmit)) {
			System.out.println("과제 평가 권한이 없음");
			return false;
		}
		
		if(homeworkSubmitDAO.deleteHomeworkEval(homeworkSubmit) != 1) {
			return false;
		}
		
		return true;
	}
	@Override
	public List<Homework> searchHomeworkClass(Homework searchCondition) {

		
		
		return homeworkDAO.searchHomeworkClass(searchCondition);
	}
	@Override
	public boolean createHomeworkSubmitToWord(HomeworkSubmit homeworkSubmit, User loginUser) {
	homeworkSubmit.setStudentNo(loginUser.getUserNo());
		
		if(!homeworkSubmitDAO.validateC(homeworkSubmit)) {
			System.out.println("과제 제출 권한이 없음");
			return false;
		}
//		if(homeworkSubmitDAO.isAlreadySubmitted(homeworkSubmit)) {
//			System.out.println("이미 제출한 과제");
//			return false;
//		}
		
		if(homeworkSubmitDAO.createHomeworkSubmit(homeworkSubmit) != 1) {
			return false;
		}
		
		return true;
	}
}
