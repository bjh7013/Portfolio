package com.ssafy.happyhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/")
public class BaseController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	String noticelist() {
		return "index";
	}
//	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	String weather() {
//		return "resources/weather";
//	}
	
	
}
