package com.ufc.br.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.model.Prato;

@Controller
@Scope("session")
@RequestMapping("/pedidos")
public class PedidosController {
	
	
	private class ItemPedido{
		public Prato prato;
		public int quantidade;
		
		public ItemPedido(Prato prato, int quantidade) {
			this.prato = prato;
			this.quantidade = quantidade;
		}
		
		@Override
		public String toString() {
			return "ItemPedido [prato=" + prato + ", quantidade=" + quantidade + "]";
		}
	}
	
	
	private List<ItemPedido> meusPedidos = new ArrayList<>();
	
	public float getValorTotal() {
		float total = 0;
		for(ItemPedido item: meusPedidos) {
			total += item.quantidade * item.prato.getPreco();
		}
		return total;
	}
	
	
	@RequestMapping("/atual")
	public ModelAndView meusPedidos() {
		ModelAndView mv = new ModelAndView("meus-pedidos");
		mv.addObject("meusPedidos", meusPedidos);
		mv.addObject("valorTotal", getValorTotal());
		return mv;
	}
	
	
	@RequestMapping("/adicionar/{prato}/{quantidade}")
	public ModelAndView adicionarAoCarrinho(@PathVariable Prato prato, @PathVariable int quantidade) {
		meusPedidos.add(new ItemPedido(prato, quantidade));
		return new ModelAndView("redirect:/pratos/listagem");
	}


	@RequestMapping("/remover-do-pedido/{id}")
	public ModelAndView removerCarrinho(@PathVariable Long id) throws IOException {
		meusPedidos.remove(getItemIndexById(id));
		ModelAndView mv = new ModelAndView("redirect:/pedidos/atual");
		return mv;
	}
	
	public int getItemIndexById(Long id) {
		for(int i = 0; i < meusPedidos.size(); i++) {
			if(meusPedidos.get(i).prato.getId() == id)
				return i;
		}
		return -1;
	}

	public List<ItemPedido> getMeusPedidos() {
		return meusPedidos;
	}
}
