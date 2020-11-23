package com.bungle.service;

import java.util.List;

import com.bungle.dto.ClassJoinList;
import com.bungle.dto.ClassSignUp;
import com.bungle.dto.Clazz;
import com.bungle.dto.User;

public interface ClassService {
	public int createClass(Clazz clazz) throws Exception;

	public int updateClass(Clazz clazz) throws Exception;

	public int deleteOneClass(Clazz clazz) throws Exception;

	public int deleteUserClass(Clazz clazz) throws Exception;

	public Clazz selectOneClass(Clazz clazz) throws Exception;

	public List<Clazz> selectUserClass(Clazz clazz) throws Exception;
	public List<Clazz> selectStudentClass(User loginUser) throws Exception;

	public int selectUserOneClass(Clazz clazz) throws Exception;
	
	public List<Clazz> selectAllClass(Clazz clazz) throws Exception;

	public int updateClassImg(int classNo, String imgpath) throws Exception;
	
	public int joinClassCall(ClassSignUp classsignup) throws Exception;
	
	public ClassSignUp checkJoinClass(ClassSignUp classsignup) throws Exception;
	
	public List<ClassJoinList> callWaitClass(ClassSignUp classsignup) throws Exception;
	
	public int updateClassJoin(ClassSignUp classsignup) throws Exception;
	
	public List<User> ClassmemberList(ClassSignUp classsignup) throws Exception;
	
	public int outClass(ClassSignUp classsignup) throws Exception;
///////////////////////////////////밑 : classtime, 위 : class ///////////////////////////////////

}
