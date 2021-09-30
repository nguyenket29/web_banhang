package com.hau.ketnguyen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.hau.ketnguyen.security.AuthenticationFailureHandler;
import com.hau.ketnguyen.security.CustomAccessDeniedHandler;
import com.hau.ketnguyen.security.CustomLogoutHandler;
import com.hau.ketnguyen.security.CustomSuccessHandler;
import com.hau.ketnguyen.service.impl.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// Các trang không yêu cầu login
				.antMatchers("/registration", "/login").permitAll()
				
				// Trang / yêu cầu phải login với vai trò USER hoặc ADMIN.
				.antMatchers("/","/cart/**","/detail/**").hasAnyAuthority("USER","ADMIN")
				.and()

				// Login
				.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/")
					.failureUrl("/login?error")
					.successHandler(successHandler())
					.failureHandler(failureHandler())
					.and()
				//Logout configuration
				.logout()
					.logoutUrl("/logout")
					.logoutSuccessHandler(logoutSuccessHandler())
				.permitAll()
				.and()
				.csrf().disable();
		
		// Khi người dùng đã login, với vai trò XX.
        // Nhưng truy cập vào trang yêu cầu vai trò YY,
        // Ngoại lệ AccessDeniedException sẽ ném ra.
		http.authorizeRequests().and()
				.exceptionHandling()
					.accessDeniedHandler(accessDeniedHandler());
		
		// Trang chỉ dành cho ADMIN
		http.authorizeRequests()
				.antMatchers("/admin/**").hasAuthority("ADMIN");
	}

	@Bean
	public CustomSuccessHandler successHandler() {
		return new CustomSuccessHandler();
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new CustomLogoutHandler();
	}
	
	@Bean
	public AuthenticationFailureHandler failureHandler() {
		AuthenticationFailureHandler failureHandler = new AuthenticationFailureHandler();
		failureHandler.setDefaultFailureUrl("/login?error");
		return failureHandler;
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public CustomUserDetailService userDetailService() {
		return new CustomUserDetailService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/resources/**", "/static/**");
	}

}
