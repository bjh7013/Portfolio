package com.bungle.service;

import java.util.List;

import com.bungle.dto.Clazz;
import com.bungle.dto.Post;

public interface PostService {
	public boolean createPost(Post post);
	public boolean updatePost(Post post);
	public boolean deletePost(Post post);
	public Post selectOnePost(int postNo);
	public List<Post> selectUserPost(int writerNo);
	public List<Post> selectAllPost();
}
