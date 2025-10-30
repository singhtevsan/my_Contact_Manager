package com.contactManager.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contactManager.Entities.User;
import com.contactManager.Helper.Message;
import com.contactManager.Repositories.UserRepository;

import jakarta.validation.Valid;

@Controller
public class AppController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/")
	public String homeHandler() {
		System.out.println("This is from home handler");
		
		return "home";
	}
	
	@GetMapping("/about")
	public String aboutHandler() {
		System.out.println("This is from about handler");
		
		return "about";
	}
	
	@GetMapping("/signUp")
	public String signUpHandler(Model model) {
		System.out.println("This is from sign up handler");
		
		// adding new user
		model.addAttribute("user", new User());
		
		return "signUp";
	}
	
	@PostMapping("/register")
	public String registerHandler(@Valid @ModelAttribute User user, BindingResult result,
			@RequestParam(value = "agreement", defaultValue = "false") boolean isAgree, Model model) {
		
		System.out.println("This is from register handler");
		
		try {
			
			if(!isAgree) {
				throw new Exception("Accept Terms and Conditions");
			}
			else if(result.hasErrors()) {
				
				model.addAttribute("user", user);
				return "signUp";
			}
			
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRole("ROLE_USER");
			user.setImageUrl("defaultProfile.png");
			user.setEnabled(true);
			
			// saving the user into the database
			User ent = userRepository.save(user);
			System.out.println("New user get saved in database " + ent);
			
			model.addAttribute("user", new User());
			model.addAttribute("message", new Message("Successfully Registered !!", "alert-success"));

			
		} catch (Exception e) {
			e.printStackTrace();
			
			model.addAttribute("user", user);
			model.addAttribute("message", new Message("Something went wrong !! " + e.getMessage(), "alert-danger"));
		}	
		
		return "signUp";
	}
	
	@GetMapping("/login")
	public String loginHandler() {
		System.out.println("This is from login handler");
		
		return "login";
	}
	
//	@GetMapping("/login-fail")
//	@ResponseBody
//	public String handleLoginFail() {
//		System.out.println("This is from handle login fail handler");
//		
//		return "Login Fails";
//	}
}
