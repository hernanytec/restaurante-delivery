package com.ufc.br.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemPedido{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Pedido pedido;
	
	@ManyToOne
	private Prato prato;
	
	private int quantidade;
	
	public ItemPedido() {
		
	}
	
	public ItemPedido(Prato prato, int quantidade) {
		this.prato = prato;
		this.quantidade = quantidade;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Prato getPrato() {
		return prato;
	}


	public void setPrato(Prato prato) {
		this.prato = prato;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	@Override
	public String toString() {
		return "ItemPedido [prato=" + prato + ", quantidade=" + quantidade + "]";
	}
}