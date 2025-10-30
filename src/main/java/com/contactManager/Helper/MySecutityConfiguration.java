package com.contactManager.Helper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecutityConfiguration {

	
	@Bean
	UserDetailsService userDetailsService() {
		
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService, 
			PasswordEncoder passwordEncoder) {
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		
		return authenticationProvider;
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
		.csrf(customizer -> customizer.disable())
		.authorizeHttpRequests(requests -> requests // New way using lambda
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/user/**").hasRole("USER")
	            .requestMatchers("/**").permitAll()
	     
	    )
		.formLogin(form -> form // New way using lambda
                .loginPage("/login")
                .defaultSuccessUrl("/user/index")
                
                // use this url in login form action(handle-login)
                .loginProcessingUrl("/handle-login") 
                
                
                //.failureUrl("/login-fail")
        );
		
		return httpSecurity.build();
	}
}
