package com.bungle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bungle.dao.ClassTimeDAO;
import com.bungle.dto.ClassTime;

@Service
public class ClassTimeServiceImpl implements ClassTimeService {

	@Autowired
	ClassTimeDAO classtimedao;

	@Override
	public int createClassTime(ClassTime classtime) throws Exception {
		if (classtimedao.createClassTime(classtime) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int updateClassTime(ClassTime classtime) throws Exception {
		if (classtimedao.updateClassTime(classtime) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteOneClassTime(ClassTime classtime) throws Exception {
		if (classtimedao.deleteOneClassTime(classtime) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int deleteUserClassTime(ClassTime classtime) throws Exception {
		if (classtimedao.deleteUserClassTime(classtime) == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public ClassTime selectOneClassTime(ClassTime classtime) throws Exception {
		return classtimedao.selectOneClassTime(classtime);
	}

	@Override
	public List<ClassTime> selectUserClassTime(ClassTime classtime) throws Exception {
		return classtimedao.selectUserClassTime(classtime);
	}

}
