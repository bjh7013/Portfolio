package com.bungle.service;

import java.util.List;

import com.bungle.dto.Parent;
import com.bungle.dto.User;

public interface UserService {
	public boolean createUser(User user);
	public boolean deleteUser(String userId, String userPw);
	public boolean updateUser(User user);
	public int selectUserById(User user);
	public User getUserbyIdandPwd(String userId,String password);
	public User getUserbyIdandNo(User user);
	public User getUserInfo(User user);
	public int makeParent(Parent parent);
	public List<User> childrenInfo(Parent parent);
	public int deleteChild(Parent parent);
	public Parent checkParent(Parent parent);
	public User myInfo(User user);
}
