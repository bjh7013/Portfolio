package com.bungle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bungle.dao.ClassSessionDAO;
import com.bungle.dto.ClassSession;

@Service
public class ClassSessionServiceImpl implements ClassSessionService {

	@Autowired
	ClassSessionDAO classsessiondao;

	@Override
	public int createClassSession(ClassSession classsession) throws Exception {
		if (classsessiondao.createClassSession(classsession) == 1) {
			return 1;
		} else {

			return 0;
		}
	}

	@Override
	public int updateClassSession(ClassSession classsession) throws Exception {
		if (classsessiondao.updateClassSession(classsession) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteOneClassSession(ClassSession classsession) throws Exception {
		if (classsessiondao.deleteOneClassSession(classsession) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteClassSession(ClassSession classsession) throws Exception {
		if (classsessiondao.deleteClassSession(classsession) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public ClassSession selectOneClassSession(ClassSession classsession) throws Exception {
		
		return classsessiondao.selectOneClassSession(classsession);
	}

	@Override
	public List<ClassSession> selectClassSession(ClassSession classsession) throws Exception {
		return classsessiondao.selectClassSession(classsession);
	}

	@Override
	public List<ClassSession> selectAllClassSession(ClassSession classsession) throws Exception {
		return classsessiondao.selectAllClassSession(classsession);
	}

}
