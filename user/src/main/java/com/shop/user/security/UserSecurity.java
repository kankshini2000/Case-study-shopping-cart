package com.shop.user.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class UserSecurity extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        .withUser("kanshi").password("sunita").roles("ADMIN"); 
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
		  .and().authorizeRequests()
		  	  .antMatchers("/user/add","/user/update/**","/user/delete/**","/user/all","/user/").permitAll()
              //.antMatchers("/admin/all","/admin/").permitAll()
		  	  .and().csrf().disable().headers().frameOptions().disable();
    }

    @Bean
	  public PasswordEncoder getPasswordEncoder() 
	  { 
		  return NoOpPasswordEncoder.getInstance(); 
	  } 
    
}
