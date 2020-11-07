package com.ssazit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.ssazit.dao.NotiDAO;
import com.ssazit.dao.UserDAO;
import com.ssazit.dto.Block;
import com.ssazit.dto.Follow;
import com.ssazit.dto.Message;
import com.ssazit.dto.Noti;
import com.ssazit.dto.User;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDAO userDao;
	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	NotiDAO notiDAO;
	
	@Override
	public List<User> selectUserList(User user) {
		// TODO Auto-generated method stub
		return userDao.selectUserList(user);
	}
	
	@Override
	public int selectUserListCount(User user) {
		// TODO Auto-generated method stub
		return userDao.selectUserListCount(user);
	}
	

	@Override
	public User selectUserOne(User user) {
		// TODO Auto-generated method stub
		return userDao.selectUserOne(user);
	}

	@Override
	public User myInfo(User user) {
		// TODO Auto-generated method stub
		user = userDao.myInfo(user);
		System.out.println("myinfo");
		System.out.println(user);
		return user;
	}
	
	@Override
	public boolean idCheck(User user) {
		// TODO Auto-generated method stub
		if(userDao.idCheck(user) == null) {
			return false;
		}else {
			return true;			
		}
	}
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		User loginUser = userDao.login(user);
		System.out.println(loginUser);
		return loginUser;
	}

	@Override
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		return userDao.insertUser(user);
	}

	@Override
	public User pwCheck(User user) throws Exception{
		// TODO Auto-generated method stub
		if(user.getUserNo() < 1 || user.getUserPw() == null || user.getUserPw().trim().equals("")) {
			throw new Exception();
		}
		return userDao.pwCheck(user);
	}

	@Override
	public User updateUser(User user) throws Exception{
		// TODO Auto-generated method stub
		if(userDao.updateUser(user) == 1) {
			return userDao.myInfo(user);	
		}else {
			throw new Exception();
		}
	}

	@Override
	public int withdrawal(User user) throws Exception {
		// TODO Auto-generated method stub
		
		if(user.getUserNo() > 0) {
			int result = userDao.withdrawal(user);
			if(result == 1) {
				return result;				
			}
		}
		throw new Exception();
	}

	@Override
	public List<Follow> selectFollowingList(User user) throws Exception {
		// TODO Auto-generated method stub
		if(user.getUserNo() > 0) {		
			return userDao.selectFollowingList(user);				
		}
		
		throw new Exception();
	}

	@Override
	public List<Follow> selectFollowerList(User user) throws Exception {
		// TODO Auto-generated method stub
		if(user.getUserNo() > 0) {		
			return userDao.selectFollowerList(user);				
		}
		
		throw new Exception();
	}
	
	@Override
	public List<Follow> selectF4FList(User user) throws Exception {
		// TODO Auto-generated method stub
		if(user.getUserNo() > 0) {		
			return userDao.selectF4FList(user);				
		}
		
		throw new Exception();
	}

	@Override
	public boolean insertFollow(int follower, int following) throws Exception {
		// TODO Auto-generated method stub
		if(follower <= 0 || following <= 0) {		
			return false;		
		}
		
		if(userDao.insertFollow(follower, following) != 1) {
			return false;
		}
		
		Noti noti = new Noti();
		noti.setSender(follower);
		noti.setReceiver(following);
		noti.setNotiType("follow");
		
		notiDAO.insertNoti(noti);
		
		User user = new User();
		user.setUserNo(follower);
		user = userDao.selectUserOne(user);
		
		noti.setNickName(user.getNickName());
		noti.setNotiMessage(user.getNickName() + "님이 나를 팔로우 했습니다.");
		
		simpMessagingTemplate.convertAndSend("/topic/pushnoti/" + following, noti);
		
		return true;
	}

	@Override
	public boolean deleteFollow(int follower, int following) throws Exception {
		if(follower > 0 && following > 0) {		
			return userDao.deleteFollow(follower, following) == 1 ? true : false;				
		}
		
		throw new Exception();
	}

	/**
	 * MESSAGE
	 */
	@Override
	public List<Message> selectSendMessageList(Message message) throws Exception {
		// TODO Auto-generated method stub
		return userDao.selectSendMessageList(message);
	}
	
	@Override
	public int selectSendMessageCount(Message message) throws Exception {
		// TODO Auto-generated method stub
		return userDao.selectSendMessageCount(message);
	}
	@Override
	public List<Message> selectReceiveMessageList(Message message) throws Exception {
		// TODO Auto-generated method stub
		return userDao.selectReceiveMessageList(message);
	}
	@Override
	public int selectReceiveMessageCount(Message message) throws Exception {
		// TODO Auto-generated method stub
		return userDao.selectReceiveMessageCount(message);
	}

	@Override
	public boolean insertMessage(Message message) throws Exception {
		// TODO Auto-generated method stub
		if(message.getMessageTo() > 0 && message.getMessageFrom() > 0) {		
		}
		if(userDao.insertMessage(message) != 1)
			return false;
		
		Noti noti = new Noti();
		noti.setSender(message.getMessageFrom());
		noti.setReceiver(message.getMessageTo());
		noti.setNotiType("message");
		
		notiDAO.insertNoti(noti);
		
		User user = new User();
		user.setUserNo(message.getMessageFrom());
		user = userDao.selectUserOne(user);
		
		noti.setNickName(user.getNickName());
		noti.setNotiMessage(user.getNickName() + "님이 나에게 메시지를 보냈습니다.");
		noti.setMessageContent(message.getContent());
		
		simpMessagingTemplate.convertAndSend("/topic/pushnoti/" + message.getMessageTo(), noti);
	
		return true;
	}

	@Override
	public boolean readMessage(int messageNo) throws Exception {
		// TODO Auto-generated method stub
		return userDao.readMessage(messageNo) == 1 ? true : false;				
	}

	@Override
	public boolean sendDelete(int messageNo, int userNo) throws Exception {
		// TODO Auto-generated method stub
		if(userNo > 0) {
			return userDao.sendDelete(messageNo, userNo) == 1 ? true : false;							
		}
		throw new Exception();
	}

	@Override
	public boolean receiveDelete(int messageNo, int userNo) throws Exception {
		// TODO Auto-generated method stub
		if(userNo > 0) {
			return userDao.receiveDelete(messageNo, userNo) == 1 ? true : false;							
		}
		
		throw new Exception();
	}

	@Override
	public List<Block> selectBlockList(User user) throws Exception {
		// TODO Auto-generated method stub
		return userDao.selectBlockList(user);
	}

	@Override
	public boolean insertBlock(int blocker, int blocking) throws Exception {
		// TODO Auto-generated method stub
		if(blocker > 0 && blocking > 0) {
			int res = userDao.insertBlock(blocker, blocking);
			
			if(res != 1)
				return false;
			
			deleteFollow(blocker, blocking);
			deleteFollow(blocking, blocker);
			
			return true;							
		}
		
		throw new Exception();
	}

	@Override
	public boolean deleteBlock(int blocker, int blocking) throws Exception {
		// TODO Auto-generated method stub
		if(blocker > 0 && blocking > 0) {
			return userDao.deleteBlock(blocker, blocking) == 1? true : false;							
		}
		
		throw new Exception();
	}

	
	@Override
	public int updateProfile(int userNo, String imgpath) throws Exception {
		User user = new User();
		user.setUserNo(userNo);
		user.setProfileImg(imgpath);
		System.out.println("service | " + user);
		return userDao.updateProfile(user);
	}

	@Override
	public User modifyNickName(User user) throws Exception {
		// TODO Auto-generated method stub
		if(userDao.updateNickName(user) == 1) {
			return userDao.myInfo(user);	
		}else {
			throw new Exception();
		}
	}

	@Override
	public List<Noti> selectMyNoti(Noti noti) {
		// TODO Auto-generated method stub
		
		return notiDAO.selectMyNoti(noti);
	}

	@Override
	public boolean deleteNoti(Noti noti) {
		// TODO Auto-generated method stub
		return notiDAO.deleteNoti(noti) == 1? true : false;
	}

}
