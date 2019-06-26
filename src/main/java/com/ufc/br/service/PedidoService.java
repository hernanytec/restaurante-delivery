package com.ufc.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Pedido;
import com.ufc.br.model.Usuario;
import com.ufc.br.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepo;
	
	public void salvar(Pedido pedido) {
		pedidoRepo.save(pedido);
	}
	
	public List<Pedido> getHistorico(Usuario usuario){
		return pedidoRepo.findByUsuarioOrderByDataDesc(usuario);
	}
	
}
