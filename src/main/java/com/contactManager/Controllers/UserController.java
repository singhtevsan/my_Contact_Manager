package com.contactManager.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/index")
	public String indexHandler() {
		System.out.println("This is from index handler");
		
		return "normal/user-index";
	}
}
