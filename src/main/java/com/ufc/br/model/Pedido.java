package com.ufc.br.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
	private LocalDateTime data;
	
	private double valor;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ItemPedido> itens;

	@ManyToOne
	private Usuario usuario;
		
	public Pedido() {
		
	}
	
	public Pedido(Usuario usuario, List<ItemPedido> itensPedido, float valorTotal, LocalDateTime dateTime) {
		this.usuario = usuario;
		this.itens = itensPedido;
		this.valor = valorTotal;
		this.data = dateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getData() {
		return DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' hh:mm a").format(data);
	}

	public void setData(LocalDateTime data) {
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
