package com.contactManager.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contactManager.Entities.Password;
import com.contactManager.Entities.User;
import com.contactManager.Helper.Message;
import com.contactManager.Repositories.UserRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/set")
public class SettingController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@ModelAttribute
	private void addCommonData(Model model, Principal principal) {
		System.out.println("Adding common data to user controller");
		
		// getting logged in user name using principal
		String username = principal.getName();
		
		System.out.println(username);
		
		// getting details of user
		User user = userRepository.getUserByUserName(username);
		
		model.addAttribute("user", user);
	}
	
	@GetMapping("/settings")
	public String showSettings(Model model) {
		System.out.println("This is from show settings page handler");
		
		model.addAttribute("password", new Password());
		return "normal/setting-page";
	}
	
	
	@PostMapping("/changePassword")
	public String changePassword(@Valid @ModelAttribute Password password, BindingResult result,
			Principal principal, Model model) {
		
		System.out.println("This is from change password handler");
		
		if(result.hasErrors()) {
			return "normal/setting-page";
		}
		
		
		// getting logged in user
		String username = principal.getName();
		User user = userRepository.getUserByUserName(username);

		String originalPassword = user.getPassword();
		
		String oldPassword = password.getOldPass();
		String newPassword = password.getNewPass();
		String confirmPassword = password.getConfirmPass();
		
		
		if(!passwordEncoder.matches(oldPassword, originalPassword)) {
			
			model.addAttribute("message", new Message("Old password is not correct !!", "alert-warning"));
			return "normal/setting-page";
		}
		else if(!newPassword.equals(confirmPassword)) {
			model.addAttribute("message", new Message("Re-Confirm password is not correct !!", "alert-warning"));
			return "normal/setting-page";
		}
		
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
		model.addAttribute("message", new Message("Password updated successfully !!", "alert-success"));
		
		return "normal/setting-page";
	}
}
