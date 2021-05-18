package com.ee.y1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ee.y1.member.MemberMapper;

@Component
public class MemberIDInterceptor implements HandlerInterceptor{

	@Autowired
	private MemberMapper memberMapper; 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//모든 요청은 request에 다 들어있음.
		String username = request.getParameter("username");
		
		//Interceptor에서도 Mapper 호출 가능
		//memberMapper.setMemberJoin(null);
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
}
