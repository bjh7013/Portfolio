package com.ssazit;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
//    @RequestMapping("/")
    public void index(HttpServletResponse response) {
        // Redirect to CKFinder's samples.
        response.setHeader("Location", "/ckfinder/static/samples/index.html");
        response.setStatus(302);
    }
//    @GetMapping("/error")
//	public String getErrorPath() {
//		// TODO Auto-generated method stub
//    	System.out.println("ssss");
//		return "/index.html";
//	}
}