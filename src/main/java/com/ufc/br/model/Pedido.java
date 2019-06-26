package com.ufc.br.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date data;
	
	private double valor;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ItemPedido> itens;

	@ManyToOne
	private Usuario usuario;
		
	public Pedido() {
		
	}
	
	public Pedido(Usuario usuario, List<ItemPedido> itensPedido, float valorTotal, Date dataPedido) {
		this.usuario = usuario;
		this.itens = itensPedido;
		this.valor = valorTotal;
		this.data = dataPedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}
	
	public String pratosDoPedido() {
		String nomes = "Itens do pedido: ";
		for(ItemPedido item : itens) {
			nomes = nomes.concat(item.getPrato().getNome()).concat(", ");
		}
		return nomes.substring(0, nomes.length()-2);
	}
}
