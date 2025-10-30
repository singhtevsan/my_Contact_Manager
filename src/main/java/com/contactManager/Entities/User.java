package com.contactManager.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user_app")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int uId;
	
	@NotBlank(message = "Name should not be blank")
	@Size(min = 4, max = 15, message = "Name should be 4-15 characters long")
	private String name;
	
	@Column(unique = true)
	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	private String email;
	
	@NotBlank(message = "Please provide the password")
	private String password;
	
	@Column(length = 100)
	private String about;
	
	private String role;
	private String imageUrl;
	private boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<Contact> contacts;
	
	public User() {
		super();
		
	}

	public User(int uId, String name, String email, String password, String about, String role, String imageUrl,
			boolean enabled) {
		super();
		this.uId = uId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.role = role;
		this.imageUrl = imageUrl;
		this.enabled = enabled;
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		return "User [uId=" + uId + ", name=" + name + ", email=" + email + ", password=" + password + ", about="
				+ about + ", role=" + role + ", imageUrl=" + imageUrl + ", enabled=" + enabled + ", contacts="
				+ contacts + "]";
	}

		
}
