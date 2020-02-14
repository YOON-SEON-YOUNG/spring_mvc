package com.kh.sample01.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kh.sample01.domain.LoginDto;
import com.kh.sample01.domain.MemberVo;
import com.kh.sample01.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Inject
	private MemberService memberService;
	
	// 로그인 폼
	@RequestMapping(value = "/loginGet", method = RequestMethod.GET)
	public String loginGet() throws Exception {
		
		return "member/login";	// views/member/login.jsp
		
	}
	
	
	// 로그인 처리
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public String loginPost(	HttpSession session, 
								LoginDto loginDto, 
								Model model) throws Exception {
		
		System.out.println("loginDto: " + loginDto);
		
		MemberVo memberVo = memberService.login(loginDto);
		
		if	(memberVo == null) {	// 로그인을 실패한 경우
			return "redirect:/member/loginGet";
		}
		
		String targetLocation = (String) session.getAttribute("targetLocation");
		String redirectPage = "/board/listAll";
		
		if (targetLocation != null) { //targetLocation이 없다면
			redirectPage = targetLocation;
		}
		System.out.println("redirectPage: " + redirectPage);
				
		// 그렇지 않은 경우
		model.addAttribute("memberVo", memberVo);
		return "redirect:" + redirectPage;
		
	}
	
	// 로그아웃 처리
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
		
		return "redirect:/";
	}
	
}
