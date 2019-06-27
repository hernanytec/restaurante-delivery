package com.ufc.br.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Usuario;
import com.ufc.br.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	public void salvar(Usuario usuario) {
		usuarioRepo.save(usuario);
	}
	
	public Usuario buscarPorId(Long id) {
		return usuarioRepo.getOne(id);
	}
	
	@Override
	public Usuario loadUserByUsername(String email) throws UsernameNotFoundException{
		return usuarioRepo.findByEmail(email);
	}
}
