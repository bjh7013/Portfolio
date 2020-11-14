package com.ssafy.happyhouse.dto;

public class User {
	private String userid;
	private String userpw;
	private String username;
	private String useraddr;
	private String userphone;
	private String usertype;
	private String userquestion;
	private String usersol;

	public User(String userid, String userpw, String username, String useraddr, String userphone, String usertype,
			String userquestion, String usersol) {
		super();
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.useraddr = useraddr;
		this.userphone = userphone;
		this.usertype = usertype;
		this.userquestion = userquestion;
		this.usersol = usersol;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", userpw=" + userpw + ", username=" + username + ", useraddr=" + useraddr
				+ ", userphone=" + userphone + ", usertype=" + usertype + ", userquestion=" + userquestion
				+ ", usersol=" + usersol + "]";
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUseraddr() {
		return useraddr;
	}

	public void setUseraddr(String useraddr) {
		this.useraddr = useraddr;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getUserquestion() {
		return userquestion;
	}

	public void setUserquestion(String userquestion) {
		this.userquestion = userquestion;
	}

	public String getUsersol() {
		return usersol;
	}

	public void setUsersol(String usersol) {
		this.usersol = usersol;
	}

}
