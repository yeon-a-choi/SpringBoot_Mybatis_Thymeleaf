package com.ee.y1.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * =======================
 * member Controller
 * =======================
 */

@Controller
@RequestMapping("/member/**")
public class MemberController {

	@GetMapping("memberJoin")
	public String setjoin() throws Exception {
		return "member/memberJoin";
	}
	
}
