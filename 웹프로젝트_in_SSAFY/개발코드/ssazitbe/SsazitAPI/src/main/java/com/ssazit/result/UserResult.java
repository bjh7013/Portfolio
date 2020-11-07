package com.ssazit.result;

import com.ssazit.dto.User;

public class UserResult {
	private String status;
	private User user;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "UserResult [status=" + status + ", user=" + user + "]";
	}	
}
