package com.bungle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bungle.dao.UserDAO;
import com.bungle.dto.Parent;
import com.bungle.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDao;

	@Override
	public boolean createUser(User user) {
		if (userDao.createUser(user) == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteUser(String userId, String userPw) {
		if (userDao.deleteUser(userId, userPw) == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateUser(User user) {
		if (userDao.updateUser(user) == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	

	@Override
	public User getUserbyIdandPwd(String userId, String password) {
		return userDao.selectUserByIdAndPwd(userId, password);

	}

	@Override
	public User getUserbyIdandNo(User user) {
		return userDao.getUserbyIdandNo(user);
	}

	@Override
	public User getUserInfo(User user) {
		return userDao.getUserInfo(user);
	}

	@Override
	public int makeParent(Parent parent) {
		return userDao.makeParent(parent);
	}

	@Override
	public List<User> childrenInfo(Parent parent) {
		return userDao.childrenInfo(parent);
	}

	@Override
	public int deleteChild(Parent parent) {
		return userDao.deleteChild(parent);
	}

	@Override
	public Parent checkParent(Parent parent) {
		return userDao.checkParent(parent);
	}

	@Override
	public int selectUserById(User user) {
		return userDao.selectUserById(user);
	}

	@Override
	public User myInfo(User user) {
		return userDao.myInfo(user);
	}

}
