package com.ee.y1.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * =======================
 * member Controller
 * =======================
 */

@Controller
@RequestMapping("/member/**")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("login")
	public String memberLogin() throws Exception{		
		return "member/memberLogin";
	}
	

	@GetMapping("join")
	public String setmemberJoin() throws Exception {
		return "member/memberJoin";
	}
	
	@PostMapping("join")
	public String setmemberJoin(MemberVO memberVO, MultipartFile avatar) throws Exception {
		
		int result = memberService.setMemberJoin(memberVO, avatar);
		
		return "redirect:../";
	}
	
	
	
}
