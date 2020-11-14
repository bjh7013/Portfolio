package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.UserDao;
import com.ssafy.happyhouse.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao dao = null;

	@Override
	public List<User> selectUserAll() {
		// TODO Auto-generated method stub
		return dao.selectUserAll();
	}

	@Override
	public User selectUser(User user) {
		// TODO Auto-generated method stub
		return dao.selectUser(user);
	}

	@Override
	public boolean createUser(User user) {
		// TODO Auto-generated method stub
		return dao.createUser(user);
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return dao.deleteUser(user);
	}

	@Override
	public boolean modifyUser(User user) {
		// TODO Auto-generated method stub
		return dao.modifyUser(user);
	}

	@Override
	public User loginUser(User user) {
		// TODO Auto-generated method stub
		return dao.loginUser(user);
	}

	@Override
	public User pwquizUser(User user) {
		// TODO Auto-generated method stub
		return dao.pwquizUser(user);
	}

	@Override
	public String pwsolUser(User user) {
		// TODO Auto-generated method stub
		return dao.pwsolUser(user);
	}

	

	

}
