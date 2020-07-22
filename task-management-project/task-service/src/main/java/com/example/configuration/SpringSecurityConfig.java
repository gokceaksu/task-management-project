package com.example.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/users").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/tasks/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/tasks").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/tasks").hasRole("USER")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

}
