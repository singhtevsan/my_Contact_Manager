package com.contactManager.Entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Password {

	@NotBlank
	private String oldPass;
	
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{6,}$",
			message = "Password must contain at least one Capital, Special and 6 Characters")
	private String newPass;
	
	@NotBlank
	private String confirmPass;
	
	public String getOldPass() {
		return oldPass;
	}
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	public String getConfirmPass() {
		return confirmPass;
	}
	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
	
	
}
