package com.bungle.dao;

import java.util.List;

import com.bungle.dto.Post;

public interface PostDAO {
	public int createPost(Post post);
	public int updatePost(Post post);
	public int deletePost(Post post);
	public Post selectOnePost(int postNo);
	public List<Post> selectUserPost(int writerNo);
	public List<Post> selectAllPost();
}
