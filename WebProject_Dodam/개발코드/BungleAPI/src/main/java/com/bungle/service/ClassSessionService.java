package com.bungle.service;

import java.util.List;

import com.bungle.dto.ClassSession;

public interface ClassSessionService {
	public int createClassSession(ClassSession classsession) throws Exception;

	public int updateClassSession(ClassSession classsession) throws Exception;

	public int deleteOneClassSession(ClassSession classsession) throws Exception;

	public int deleteClassSession(ClassSession classsession) throws Exception;

	public ClassSession selectOneClassSession(ClassSession classsession) throws Exception;

	public List<ClassSession> selectClassSession(ClassSession classsession) throws Exception;

	public List<ClassSession> selectAllClassSession(ClassSession classsession) throws Exception;
}
