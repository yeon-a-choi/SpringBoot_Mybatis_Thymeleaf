package com.ee.y1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ee.y1.member.MemberVO;

public class TestInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("member");
		MemberVO memberVO = null;
		boolean result = false;
		
		if(obj != null) {
			memberVO = (MemberVO)obj;
			if(memberVO.getUsername().equals("admin")) {
				result = true;
			}
		}
		
		if(!result) {		 
			 response.sendRedirect("/member/login");
		 }
		
		return result;
	}

}
