package com.ufc.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Usuario;
import com.ufc.br.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	public void salvar(Usuario usuario) {
		usuarioRepo.save(usuario);
	}
	
	public Usuario buscarPorId(Long id) {
		return usuarioRepo.getOne(id);
	}
}
