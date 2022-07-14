package com.srg.locadora.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	
	private Double desconto;
	private Integer quantidadeDeDiasDeAluguel;
	private Double precoPorDia;
	
	public ItemPedido() {
	}

	public ItemPedido(Pedido pedido, Dvd dvd, Double desconto, Integer quantidade, Double preco) {
		super();
		id.setPedido(pedido);
		id.setDvd(dvd);
		this.desconto = desconto;
		this.quantidadeDeDiasDeAluguel = quantidade;
		this.precoPorDia = preco;
	}

	public double getSubTotal() {
		return ((precoPorDia*quantidadeDeDiasDeAluguel) - desconto);
	}
	
	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}
	
	public Dvd getDvd() {
		return id.getDvd();
	}
	
	public void setDvd(Dvd dvd) {
		id.setDvd(dvd);
	}
	
	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}


	public Integer getQuantidadeDeDiasDeAluguel() {
		return quantidadeDeDiasDeAluguel;
	}

	public void setQuantidadeDeDiasDeAluguel(Integer quantidadeDeDiasDeAluguel) {
		this.quantidadeDeDiasDeAluguel = quantidadeDeDiasDeAluguel;
	}

	public Double getPrecoPorDia() {
		return precoPorDia;
	}

	public void setPrecoPorDia(Double precoPorDia) {
		this.precoPorDia = precoPorDia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(getDvd().getNome());
		builder.append(", Qte: ");
		builder.append(getQuantidadeDeDiasDeAluguel());
		builder.append(", Preço unitário: ");
		builder.append(nf.format(getPrecoPorDia()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}
}

