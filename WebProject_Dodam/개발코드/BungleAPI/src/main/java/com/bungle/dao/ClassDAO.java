package com.bungle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bungle.dto.ClassJoinList;
import com.bungle.dto.ClassSignUp;
import com.bungle.dto.Clazz;
import com.bungle.dto.User;

@Mapper
public interface ClassDAO {
	public int createClass(Clazz clazz);

	public int updateClass(Clazz clazz);

	public int deleteOneClass(Clazz clazz);

	public int deleteUserClass(Clazz clazz);

	public Clazz selectOneClass(Clazz clazz);

	public List<Clazz> selectUserClass(Clazz clazz);
	public List<Clazz> selectStudentClass(User student);

	public int selectUserOneClass(Clazz clazz);

	public List<Clazz> selectAllClass(Clazz clazz);

	public int updateClassImg(Clazz clazz);

	public int joinClassCall(ClassSignUp classsignup);

	public ClassSignUp checkJoinClass(ClassSignUp classsignup);

	public List<ClassJoinList> callWaitClass(ClassSignUp classsignup);

	public int updateClassJoin(ClassSignUp classsignup);
	
	public List<User> ClassmemberList(ClassSignUp classsignup);
	
	public int outClass(ClassSignUp classsignup);
	
}
