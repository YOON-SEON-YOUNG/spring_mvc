package com.kh.sample01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {
	
	
	@RequestMapping("/doA")
	public void doA() {
		System.out.println("doA 실행됨..");
		// return "doA"; // /WEB-INF/views/doA.jsp
		// void인 경우에는 요청명이 파일명
	}
	
	@RequestMapping("/doB")
	public void doB() {
		System.out.println("doB 실행됨..");
	}
	
}
