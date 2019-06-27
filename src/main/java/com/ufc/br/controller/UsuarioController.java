package com.ufc.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Usuario;
import com.ufc.br.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping("/cadastro")
	public ModelAndView caregarFormCadastro() {
		ModelAndView mv = new ModelAndView("cadastro-usuario");
		mv.addObject(new Usuario());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarUsuario(Usuario usuario) {
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		
		usuarioService.salvar(usuario);
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
	
}
