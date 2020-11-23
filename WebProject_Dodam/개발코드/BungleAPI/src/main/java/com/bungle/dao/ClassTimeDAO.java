package com.bungle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bungle.dto.ClassTime;

@Mapper
public interface ClassTimeDAO {
	public int createClassTime(ClassTime classtime);

	public int updateClassTime(ClassTime classtime);

	public int deleteOneClassTime(ClassTime classtime);

	public int deleteUserClassTime(ClassTime classtime);

	public ClassTime selectOneClassTime(ClassTime classtime);

	public List<ClassTime> selectUserClassTime(ClassTime classtime);
}
