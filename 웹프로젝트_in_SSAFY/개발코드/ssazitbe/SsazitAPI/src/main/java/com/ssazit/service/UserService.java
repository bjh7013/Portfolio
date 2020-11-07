package com.ssazit.service;

import java.util.List;

import com.ssazit.dto.Block;
import com.ssazit.dto.Follow;
import com.ssazit.dto.Message;
import com.ssazit.dto.Noti;
import com.ssazit.dto.User;

public interface UserService {
	public List<User> selectUserList(User user);
	public int selectUserListCount(User user);
	public User selectUserOne(User user);
	public User myInfo(User user);
	public boolean idCheck(User user);
	public User login(User user);
	public User pwCheck(User user) throws Exception;
	public int insertUser(User user);
	public User updateUser(User user) throws Exception;
	public User modifyNickName(User user) throws Exception;
	public int withdrawal(User user) throws Exception;
	public int updateProfile(int userNo, String imgpath) throws Exception;
	
	//팔로우
	public List<Follow> selectFollowingList(User user) throws Exception;
	public List<Follow> selectFollowerList(User user) throws Exception;
	public List<Follow> selectF4FList(User user) throws Exception;
	public boolean insertFollow(int follower, int following) throws Exception;
	public boolean deleteFollow(int follower, int following) throws Exception;
	
	//메시지
	public List<Message> selectSendMessageList(Message message) throws Exception;
	public int selectSendMessageCount(Message message) throws Exception;
	public List<Message> selectReceiveMessageList(Message message) throws Exception;
	public int selectReceiveMessageCount(Message message) throws Exception;
	public boolean insertMessage(Message message) throws Exception;
	public boolean readMessage(int messageNo) throws Exception;
	public boolean sendDelete(int messageNo, int userNo) throws Exception;
	public boolean receiveDelete(int messageNo, int userNo) throws Exception;
	
	//블록
	public List<Block> selectBlockList(User user) throws Exception;
	public boolean insertBlock(int blocker, int blocking) throws Exception;
	public boolean deleteBlock(int blocker, int blocking) throws Exception;

	public List<Noti> selectMyNoti(Noti noti);
	public boolean deleteNoti(Noti noti);
}
