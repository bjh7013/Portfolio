package com.bungle.service;

import java.util.List;

import com.bungle.dto.ClassTime;
import com.bungle.dto.Clazz;

public interface ClassTimeService {
	public int createClassTime(ClassTime classtime) throws Exception;

	public int updateClassTime(ClassTime classtime) throws Exception;

	public int deleteOneClassTime(ClassTime classtime) throws Exception;

	public int deleteUserClassTime(ClassTime classtime) throws Exception;

	public ClassTime selectOneClassTime(ClassTime classtime) throws Exception;

	public List<ClassTime> selectUserClassTime(ClassTime classtime) throws Exception;
}
