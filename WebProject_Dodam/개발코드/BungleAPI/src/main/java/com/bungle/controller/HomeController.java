package com.bungle.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController implements ErrorController{

	@Override
	@GetMapping("/error")
	public String getErrorPath() {
		// TODO Auto-generated method stub
		System.out.println("dddddddddddddddddddddddddd");
		return "index.html";
	}
}