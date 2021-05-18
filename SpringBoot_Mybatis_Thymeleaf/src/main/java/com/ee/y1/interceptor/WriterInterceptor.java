package com.ee.y1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ee.y1.board.BoardVO;
import com.ee.y1.member.MemberVO;

@Component
public class WriterInterceptor implements HandlerInterceptor{
	
	// Controller 종류 후 실행하는 Interceptor
	// 작성자를 출력
	// 로그인 유저네임 출력
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		//0. Method 형식
		String method = request.getMethod();
		
		if(method.equals("GET")) {

		}
		
		// 1. 로그인 한 유저네임
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		//System.out.println("username(login) : "+memberVO.getUsername());
		
		
		//2. 작성자
		BoardVO boardVO = (BoardVO)modelAndView.getModel();
		//System.out.println("username(writer) : "+boardVO.getWriter());
		
		//3. 유저와 작성자가 일치X
		//	common/result로 이동
		//	alert창에 작성자가 아님 메세지 띄움
		//	list로 이동
		if(memberVO != null && boardVO != null) {
			
			if(!memberVO.getUsername().equals(boardVO.getWriter())) {
				modelAndView.setViewName("common/result");
				modelAndView.addObject("msg", "작성자가 아님");
				modelAndView.addObject("path", "./list");
			}
			
		} else {
			modelAndView.setViewName("common/result");
			modelAndView.addObject("msg", "로그인이 필요");
			modelAndView.addObject("path", "../member/login");			
		}
		
		
		
		//4. login을 하지 않았으면
		//	common/result로 이동
		//	alert창에 로그인 필요 메세지 띄움
		//	list로 이동
		
	}
	
	// WriterInterceptorConfig
	// /qna/update

}
