package com.contactManager.Controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.contactManager.Entities.Contact;
import com.contactManager.Entities.User;
import com.contactManager.Helper.Message;
import com.contactManager.Repositories.UserRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	
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

	@GetMapping("/index")
	public String indexHandler() {
		System.out.println("This is from index handler");
		
		return "normal/user-dashboard";
	}
	
	
	@GetMapping("/addContact")
	public String showAddContact(Model model) {
		System.out.println("This is from show add contact handler");
		
		// adding object of contact
		model.addAttribute("contact", new Contact());
		
		return "normal/add-contact";
	}
	
	
	@PostMapping("/process-contact")
	public String handleAddContact(@Valid @ModelAttribute Contact contact, BindingResult result,
			@RequestParam("profileImage") MultipartFile file, Principal principal, Model model) {
		
		System.out.println("This is from handle contact handler");
		
		try {
		
				if(result.hasErrors()) {
					return "normal/add-contact";
				}
				
				// getting logged in user name
				String username = principal.getName();
				
				// getting user from database
				User user = userRepository.getUserByUserName(username);
				
				// adding contact to user
				user.getContacts().add(contact);
				
				// adding user to contact
				contact.setUser(user);
				
				// processing and uploading file
				if(!file.isEmpty()) {
					
					contact.setImageUrl(file.getOriginalFilename());
					
					// file object with the path of folder
					File fileObject = new ClassPathResource("static/img").getFile();
					
					// absolute path
					Path path = Paths.get(fileObject.getAbsolutePath() + File.separator + file.getOriginalFilename());
					
					Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				}
				else {
					System.out.println("No profile pic selected");
					contact.setImageUrl("defaultProfile.png");
				}
				
				// saving the contact
				userRepository.save(user);
				
				model.addAttribute("message", new Message("Contact added successfully !!", "alert-success"));
				System.out.println("Contact saved to db");
			
		} catch (Exception e) {
			
			System.out.println("ERROR--------->" + e.getMessage());
			model.addAttribute("message", new Message(e.getMessage(), "alert-danger"));
		}
		
		return "normal/add-contact";
	}
}
