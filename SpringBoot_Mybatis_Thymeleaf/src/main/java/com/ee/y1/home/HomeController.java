package com.ee.y1.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * =======================
 * Thymeleaf Controller
 * =======================
 */

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {
		
		model.addAttribute("message", "Thymeleaf Project");
		
		return "index";
	}

}
