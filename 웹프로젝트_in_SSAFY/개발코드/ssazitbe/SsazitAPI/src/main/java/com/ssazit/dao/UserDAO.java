package com.ssazit.dao;

import java.util.List;

import com.ssazit.dto.Article;
import com.ssazit.dto.Block;
import com.ssazit.dto.Follow;
import com.ssazit.dto.Message;
import com.ssazit.dto.User;

public interface UserDAO {
	public List<User> selectUserList(User user);
	public int selectUserListCount(User user);
	public User selectUserOne(User user);
	public User myInfo(User user);
	public User idCheck(User user);
	public User login(User user);
	public User pwCheck(User user) throws Exception;
	public int insertUser(User user);
	public int updateUser(User user) throws Exception;
	public int updateNickName(User user) throws Exception;
	public int withdrawal(User user) throws Exception;
	public int updateProfile(User user) throws Exception;
	
	//팔로우
	public List<Follow> selectFollowingList(User user) throws Exception;
	public List<Follow> selectFollowerList(User user) throws Exception;
	public List<Follow> selectF4FList(User user);
	public int insertFollow(int follower, int following) throws Exception;
	public int deleteFollow(int follower, int following) throws Exception;
	public User isF4F(Article searchCondition);
	public User isFollowing(Follow follow);
	//메세지
	public List<Message> selectSendMessageList(Message message) throws Exception;
	public int selectSendMessageCount(Message message) throws Exception;
	public List<Message> selectReceiveMessageList(Message message) throws Exception;
	public int selectReceiveMessageCount(Message message) throws Exception;
	public int insertMessage(Message message) throws Exception;
	public int readMessage(int messageNo) throws Exception;
	public int sendDelete(int messageNo, int userNo) throws Exception;
	public int receiveDelete(int messageNo, int userNo) throws Exception;

	//블록
	public List<Block> selectBlockList(User user) throws Exception;
	public int insertBlock(int blocker, int blocking) throws Exception;
	public int deleteBlock(int blocker, int blocking) throws Exception;
}