package com.example.demo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.config.ApiPath;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	MyUserDetailsService myUserDetailsService;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/api/v1/class_batch/create").hasAnyAuthority("FA_MANAGEMENT", "DELIVERY_MANAGEMENT", "SYSTEM_ADMIN")
			.antMatchers("/api/v1/class_batch/update/**").hasAnyAuthority("FA_MANAGEMENT", "DELIVERY_MANAGEMENT", "SYSTEM_ADMIN")
			.antMatchers("/api/v1/class_batch/delete/**").hasAnyAuthority("FA_MANAGEMENT", "SYSTEM_ADMIN")
			.antMatchers("/api/v1/trainer/create/**").hasAnyAuthority("FA_MANAGEMENT", "SYSTEM_ADMIN")
			.antMatchers("/api/v1/trainer/update/**").hasAnyAuthority("FA_MANAGEMENT", "ADMIN", "SYSTEM_ADMIN", "TRAINER")
			.antMatchers("/api/v1/trainer/delete/**").hasAnyAuthority("FA_MANAGEMENT", "SYSTEM_ADMIN")
			.antMatchers("/api/v1/candidate/create/**").hasAnyAuthority("FA_MANAGEMENT", "FA_REC", "SYSTEM_ADMIN")
			.antMatchers("/api/v1/candidate/update/**").hasAnyAuthority("FA_MANAGEMENT", "FA_REC", "SYSTEM_ADMIN")
			.antMatchers("/api/v1/candidate/delete/**").hasAnyAuthority("FA_MANAGEMENT", "FA_REC", "SYSTEM_ADMIN")
			.antMatchers("/api/v1/candidate/transfer/**").hasAnyAuthority("FA_MANAGEMENT", "FA_REC", "SYSTEM_ADMIN")
			.antMatchers("/api/v1/candidate_result/update/**").hasAnyAuthority("FA_MANAGEMENT", "FA_REC", "SYSTEM_ADMIN")
			//login
			.anyRequest().authenticated()
			.and().formLogin().loginPage(ApiPath.API_DOMAIN + "/login/").permitAll()
			.defaultSuccessUrl(ApiPath.API_DOMAIN+"/login?success=true")
			.failureUrl(ApiPath.API_DOMAIN+"/login?success=false")
			.usernameParameter("username")
			.passwordParameter("password")
			.loginProcessingUrl("/j_spring_security_check")
			.permitAll()
			//csrf
			.and()
			.csrf().disable()
			//logout
			.logout()
			.logoutUrl(ApiPath.API_DOMAIN + "/logout/")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.and()
			//get user from db
			.userDetailsService(myUserDetailsService)
            .exceptionHandling().accessDeniedPage("/403");
		
		//JWT Filter
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**",  "/js/**", "/img/**", "**/templates/**");
    }
	
}
