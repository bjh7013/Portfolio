package com.bungle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bungle.dao.PostDAO;
import com.bungle.dto.Post;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostDAO postDao;
	
	@Override
	public boolean createPost(Post post) {
		
		if(postDao.createPost(post)==1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean updatePost(Post post) {
			if(postDao.updatePost(post)==1) {
				return true;
			}else {
				return false;
			}
	}	

	@Override
	public boolean deletePost(Post post) {
		if(postDao.deletePost(post)==1) {
			return true;
		}else {
			return false;
		}
	}


	@Override
	public Post selectOnePost(int postNo) {
		return postDao.selectOnePost(postNo);
	}

	@Override
	public List<Post> selectUserPost(int writerNo) {
		return postDao.selectUserPost(writerNo);
	}

	@Override
	public List<Post> selectAllPost() {
		return postDao.selectAllPost();
	}
	
}	
