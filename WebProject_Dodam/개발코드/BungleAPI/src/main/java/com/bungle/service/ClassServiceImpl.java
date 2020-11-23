package com.bungle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bungle.dao.ClassDAO;
import com.bungle.dto.ClassJoinList;
import com.bungle.dto.ClassSignUp;
import com.bungle.dto.Clazz;
import com.bungle.dto.User;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	ClassDAO classdao;

	@Override
	public int createClass(Clazz clazz) throws Exception {
		if (classdao.createClass(clazz) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int updateClass(Clazz clazz) throws Exception {
		if (classdao.updateClass(clazz) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteOneClass(Clazz clazz) throws Exception {
		if (classdao.deleteOneClass(clazz) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteUserClass(Clazz clazz) throws Exception {
		if (classdao.deleteUserClass(clazz) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Clazz selectOneClass(Clazz clazz) throws Exception {
		return classdao.selectOneClass(clazz);
	}

	@Override
	public List<Clazz> selectUserClass(Clazz clazz) throws Exception {
		return classdao.selectUserClass(clazz);
	}

	@Override
	public List<Clazz> selectAllClass(Clazz clazz) throws Exception {
		return classdao.selectAllClass(clazz);
	}

	@Override
	public int selectUserOneClass(Clazz clazz) throws Exception {
		return classdao.selectUserOneClass(clazz);
	}

	@Override
	public int updateClassImg(int classNo, String imgpath) throws Exception {
		Clazz clazz = new Clazz();
		clazz.setClassNo(classNo);
		clazz.setClassImg(imgpath);
		System.out.println(clazz+"이미지서비스");
		return classdao.updateClassImg(clazz);
	}

	@Override
	public int joinClassCall(ClassSignUp classsignup) throws Exception {
		return classdao.joinClassCall(classsignup);
	}

	@Override
	public ClassSignUp checkJoinClass(ClassSignUp classsignup) throws Exception {
		return classdao.checkJoinClass(classsignup);
	}

	@Override
	public List<ClassJoinList> callWaitClass(ClassSignUp classsignup) throws Exception {
		return classdao.callWaitClass(classsignup);
	}

	@Override
	public int updateClassJoin(ClassSignUp classsignup) throws Exception {
		return classdao.updateClassJoin(classsignup);
	}

	@Override
	public List<User> ClassmemberList(ClassSignUp classsignup) throws Exception {
		return classdao.ClassmemberList(classsignup);
	}

	@Override
	public int outClass(ClassSignUp classsignup) throws Exception {
		return classdao.outClass(classsignup);
	}

	@Override
	public List<Clazz> selectStudentClass(User loginUser) throws Exception {
		// TODO Auto-generated method stub
		return classdao.selectStudentClass(loginUser);
	}


}
