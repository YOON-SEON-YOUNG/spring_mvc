package com.kh.sample01.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kh.sample01.domain.MemberVo;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 로그인 정보 있으면 지우고
		System.out.println("로그인 전처리");
		
		HttpSession session = request.getSession();
		MemberVo memberVo = (MemberVo) session.getAttribute("memberVo");
		
		if	(memberVo != null) {	// 값이 들어있다면 remove
			session.removeAttribute("memberVo");
		}
		return true;	// true면 계속 진행, false는 멈춤
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 해당 로그인에 대해서 처리
		System.out.println("로그인 후처리");
		
		HttpSession session = request.getSession();
		// 멤버 컨트롤에서 넘겨받은 model 부분만 쓸거임
		ModelMap modelMap = modelAndView.getModelMap();
		MemberVo memberVo = (MemberVo) modelMap.get("memberVo");
		
		if(memberVo != null) {	// 해당 값이 있따면 null이 아니다
			session.setAttribute("memberVo", memberVo);
		}
	}
}
