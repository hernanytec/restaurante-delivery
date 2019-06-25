package com.ufc.br.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/", "/usuario/cadastro", "/usuario/salvar", "/pratos/listagem").permitAll()
		.antMatchers("/css/**", "/js/**", "/img/**", "/pratos_images/**").permitAll()
		
		.anyRequest().authenticated()//hasAnyRole
		.and().formLogin()
			//.loginPage("/login/formulario")
			//.loginProcessingUrl("/login/entrar")
			//.defaultSuccessUrl("/")
		.permitAll()
		.and().logout().logoutSuccessUrl("/").permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
}
