package com.ufc.br.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Usuario;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	
	@RequestMapping("/formulario")
	public String getFormulatio() {
		return "login-formulario";
	}
	
	@RequestMapping("/entrar")
	public ModelAndView logar(Usuario usuario) {
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
}
