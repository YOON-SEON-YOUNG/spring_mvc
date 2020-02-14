package com.kh.sample01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController2 {
	
	@RequestMapping("/doC")
	public String doC() {
		System.out.println("doC 실행됨..");
		
		return "result";
	}

	
	@RequestMapping("/doD")
	public String doD(@ModelAttribute("msg") String msg) {
		// -> 요청 파라미터를 뷰까지 전달
		System.out.println("msg: " + msg);
		return "msg";	//msg.jsp
	}
	
}
