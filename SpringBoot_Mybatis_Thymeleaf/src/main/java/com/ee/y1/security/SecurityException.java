package com.ee.y1.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

//로그인 성공 후 권한 에러가 발생 했을 때 실행하는 핸들러

//1. AccessDeniedHandler Interface 구현
public class SecurityException implements AccessDeniedHandler{

	//2. handle 메서드 오버라이딩
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

//		RequestDispatcher view = request.getRequestDispatcher("경로");
//		request.setAttribute("key", "value");
//		view.forward(request, response);
		
		response.sendRedirect("/member/error");
		
	}

}
