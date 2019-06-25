package com.ufc.br.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.ufc.br.model.Usuario;
import com.ufc.br.repository.UsuarioRepository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepo.findByEmail(email);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		
		return usuario;
	}
}
