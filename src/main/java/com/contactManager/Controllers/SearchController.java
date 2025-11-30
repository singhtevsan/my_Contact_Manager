package com.contactManager.Controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.contactManager.Entities.Contact;
import com.contactManager.Entities.User;
import com.contactManager.Repositories.ContactRepository;
import com.contactManager.Repositories.UserRepository;

@RestController
public class SearchController {

	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	// Handle the search
	@GetMapping("/searchBar/{input}")
	public ResponseEntity<?> handleSearch(@PathVariable String input, Principal principal){
		System.out.println("This is from handle search handler");
		
		// getting logged in user
		String username = principal.getName();
		User user = userRepository.getUserByUserName(username);
		
		List<Contact> list = contactRepository.findByNameContainingAndUser(input, user);
		
		return ResponseEntity.ok(list);
	}
}
