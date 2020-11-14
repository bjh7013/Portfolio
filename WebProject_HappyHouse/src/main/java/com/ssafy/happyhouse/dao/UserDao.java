package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.User;
@Mapper
public interface UserDao {
	
	public List<User> selectUserAll();//전체검색
	public User selectUser(User user);//아이디검색
	public boolean createUser(User user); //생성
	public boolean deleteUser(User user); //삭제
	public boolean modifyUser(User user);//수정
	public User loginUser(User user);//로그인
	public User pwquizUser(User user);//비밀번호문제
	public String pwsolUser(User user);//비밀번호정답입력
}
