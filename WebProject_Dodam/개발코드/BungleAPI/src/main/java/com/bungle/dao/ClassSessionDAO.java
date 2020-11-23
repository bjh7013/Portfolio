package com.bungle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bungle.dto.ClassSession;

@Mapper
public interface ClassSessionDAO {
	public int createClassSession(ClassSession classsession);

	public int updateClassSession(ClassSession classsession);

	public int deleteOneClassSession(ClassSession classsession);

	public int deleteClassSession(ClassSession classsession);

	public ClassSession selectOneClassSession(ClassSession classsession);

	public List<ClassSession> selectClassSession(ClassSession classsession);

	public List<ClassSession> selectAllClassSession(ClassSession classsession);
}
