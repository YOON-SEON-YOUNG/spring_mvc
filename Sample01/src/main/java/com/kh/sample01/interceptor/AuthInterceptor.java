package com.kh.sample01.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kh.sample01.domain.MemberVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	// 로그인 전 클릭한 페이지 로그인 후 클릭한 페이지로 갈 수 있도록..
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		MemberVo memberVo = (MemberVo) session.getAttribute("memberVo");
		System.out.println("--memberVo: " + memberVo);
		
		if(memberVo == null) { // memberVo가 null이라면 로그인 전이라면
			String uri = request.getRequestURI(); 		// 가려고 하는 경로
			String query = request.getQueryString();	// 뒤로 전달되는 내용
			String targetLocation = uri + "?" + query;
			session.setAttribute("targetLocation", targetLocation);
			response.sendRedirect("/member/loginGet");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("targetLocation");
		
	}
	
	
}
