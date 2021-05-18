package com.ee.y1.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${member.filePath}")
	private String filePath;
	
	//login
	@GetMapping("login")
	public String memberLogin() throws Exception{		
		return "member/memberLogin";
	}
	
	@PostMapping("login")
	public String getLogin(MemberVO memberVO, HttpSession session)throws Exception{
		
		memberVO = memberService.memberLogin(memberVO);
		
		if(memberVO != null) {
			session.setAttribute("member", memberVO);
		}	
		
		return "redirect:/";
	}
	
	
	//logout
	@GetMapping("logout")
	public String logout(HttpSession session)throws Exception{
		session.invalidate();
		
		return "redirect:../";
	}
	
	
	//join
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
