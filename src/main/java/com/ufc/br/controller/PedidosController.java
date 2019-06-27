package com.ufc.br.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.ItemPedido;
import com.ufc.br.model.Pedido;
import com.ufc.br.model.Prato;
import com.ufc.br.model.Usuario;
import com.ufc.br.service.PedidoService;

@Controller
@Scope("session")
@RequestMapping("/pedidos")
public class PedidosController {
	
	@Autowired
	PedidoService pedidoService;
	
	Usuario usuarioLogado = null;
	
	
	private List<ItemPedido> itensPedido = new ArrayList<>();
	
	public float getValorTotal() {
		float total = 0;
		for(ItemPedido item: itensPedido) {
			if(item != null)
				total += item.getQuantidade() * item.getPrato().getPreco();
		}
		return total;
	}
	
	@RequestMapping("/atual")
	public ModelAndView itensPedido() {
		usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mv = new ModelAndView("meus-pedidos");
		mv.addObject("itensPedido", itensPedido);
		mv.addObject("valorTotal", getValorTotal());
		mv.addObject("historico", pedidoService.getHistorico(usuarioLogado));
		return mv;
	}
	
	
	@RequestMapping("/adicionar/{prato}/{quantidade}")
	public ModelAndView adicionarAoCarrinho(@PathVariable Prato prato, @PathVariable int quantidade) {
		itensPedido.add(new ItemPedido(prato, quantidade));
		return new ModelAndView("redirect:/pratos/listagem");
	}


	@RequestMapping("/remover-do-pedido/{id}")
	public ModelAndView removerCarrinho(@PathVariable Long id) throws IOException {
		itensPedido.remove(getItemIndexById(id));
		ModelAndView mv = new ModelAndView("redirect:/pedidos/atual");
		return mv;
	}
	
	@RequestMapping("/cancelar")
	public ModelAndView cancelar() throws IOException {
		itensPedido.clear();
		ModelAndView mv = new ModelAndView("redirect:/pratos/listagem");
		return mv;
	}
	
	@RequestMapping("/salvar")
	public ModelAndView salvar() throws IOException{
		pedidoService.salvar(new Pedido(usuarioLogado, itensPedido, getValorTotal(), LocalDateTime.now()));;
		
		itensPedido.clear();

		ModelAndView mv = new ModelAndView("redirect:/pratos/listagem");
		return mv;
	}
	
	public int getItemIndexById(Long pratoId) {
		for(int i = 0; i < itensPedido.size(); i++) {
			if(itensPedido.get(i).getPrato().getId() == pratoId)
				return i;
		}
		return -1;
	}

	public List<ItemPedido> getitensPedido() {
		return itensPedido;
	}
}
