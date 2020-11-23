package com.bungle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bungle.dto.Parent;
import com.bungle.dto.User;

@Mapper
public interface UserDAO {
	
	public int createUser(User user);
	public int updateUser(User user);
	public int deleteUser(String userId, String userPw);
	public int selectUserById(User user);
	public User selectUserByIdAndPwd(String userId,String userPw);
	public User getUserbyIdandNo(User user);
	public User getUserInfo(User user);
	public int makeParent(Parent parent);
	public List<User> childrenInfo(Parent parent);
	public int deleteChild(Parent parent);
	public Parent checkParent(Parent parent);
	public User myInfo(User user);
}
