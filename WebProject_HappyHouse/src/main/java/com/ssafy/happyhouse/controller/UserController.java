package com.ssafy.happyhouse.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.happyhouse.dto.User;
import com.ssafy.happyhouse.service.UserService;

@Controller
@RequestMapping(value = "/api/user")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class UserController {

	@Autowired
	UserService userservice = null;

	@RequestMapping(value = "/userlogin.do", method = RequestMethod.GET)
	String userlogin() {
		return "user/userlogin";
	}

	@RequestMapping(value = "/userlogin.do", method = RequestMethod.POST)
	String userloginreal(User user, HttpServletRequest req, Model model) {
		System.out.println(user.getUserid());
		User logins = null;
		logins = userservice.loginUser(user);
		if (logins == null) {
			model.addAttribute("msg","아이디나 비밀번호가 틀렸습니다.");
			return "user/userlogin";
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("userinfo", logins);
			return "index";
		}
	}

	@RequestMapping(value = "/usercreate.do", method = RequestMethod.GET)
	String usercreate() {
		return "user/usercreate";
	}

	@RequestMapping(value = "/usercreate.do", method = RequestMethod.POST)
	String usercreatereal(User user, Model model) {
		User checkid = null;
		checkid = userservice.selectUser(user);
		if (checkid != null) {
			model.addAttribute("msg", "아이디가 중복입니다!");
			return "index";
		}
		userservice.createUser(user);
		model.addAttribute("msg", "회원 가입이 완료되었습니다.");
		return "index";
	}

	@RequestMapping(value = "/userlogout.do", method = RequestMethod.GET)
	String userlogout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return "index";
	}

	@RequestMapping(value = "/usersearchall.do", method = RequestMethod.GET)
	String usersearchall(Model model) {
		List<User> user = null;
		user = userservice.selectUserAll();
		model.addAttribute("searchsdata", user);
		return "/user/usersearchall";
	}

	@RequestMapping(value = "/usersearch.do", method = RequestMethod.GET)
	String usersearch() {
		return "/user/usersearch";
	}

	@RequestMapping(value = "/userdelete.do", method = RequestMethod.GET)
	String userdelete() {
		return "/user/userdelete";
	}

	@RequestMapping(value = "/usermodify.do", method = RequestMethod.GET)
	String usermodify() {
		return "/user/usermodify";
	}

	@RequestMapping(value = "/usersearchpw.do", method = RequestMethod.GET)
	String usersearchpw() {
		return "/user/usersearchpw";
	}

	@RequestMapping(value = "/usersearchpw.do", method = RequestMethod.POST)
	String usersearchpwreal(User user, Model model) {
		User question = null;
		question = userservice.pwquizUser(user);
		if (question != null) {
			model.addAttribute("questions", question.getUserquestion());
			model.addAttribute("userid", question.getUserid());
			return "/user/usersearchpw";
		}else {
			model.addAttribute("msg", "존재하지 않는 아이디입니다.");
			return "/user/usersearchpw";
		}
	}

	@RequestMapping(value = "/usersearchpwsol.do", method = RequestMethod.POST)
	String usersearchpwsolreal(User user, Model model) {
		String pw = userservice.pwsolUser(user);
		if (pw == null) {
			model.addAttribute("sols", "비밀번호 정답이 맞지 않습니다!");
			return "/user/usersearchpw";
		} else {
			model.addAttribute("sols", "찾은 비밀번호는 " + pw + " 입니다.");
			return "/user/usersearchpw";
		}
	}

	@RequestMapping(value = "/usermodify.do", method = RequestMethod.POST)
	String usermodifyreal(User user, HttpServletRequest req) {
		userservice.modifyUser(user);
		HttpSession session = req.getSession();
		session.invalidate();
		session = req.getSession();
		User logins = userservice.loginUser(user);
		session.setAttribute("userinfo", logins);
		return "index";
	}

	@RequestMapping(value = "/userdelete.do", method = RequestMethod.POST)
	String userdeletereal(User user) {
		userservice.deleteUser(user);
		return "/user/userdelete";
	}

	@RequestMapping(value = "/usersearch.do", method = RequestMethod.POST)
	String usersearchreal(User user, Model model) {
		User u = null;
		u = userservice.selectUser(user);
		model.addAttribute("searchs", u);
		model.addAttribute("noneuser", "존재하지 않는 유저입니다.");
		return "/user/usersearch";			
	}

	@RequestMapping(value = "/usersearch.do/{userid}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> checkid(@PathVariable String userid) {
		User u = null;
		User idcheck = new User();
		idcheck.setUserid(userid);
		u = userservice.selectUser(idcheck);
		System.out.println("u" + u);
		if (u == null) {
			return new ResponseEntity<String>("yes", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("no", HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/usermenu.do", method = RequestMethod.GET)
	String usermenu(HttpServletRequest req) {
		return "/user/usermenu";
	}

}
