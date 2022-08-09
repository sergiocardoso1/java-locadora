package com.srg.locadora.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.srg.locadora.domain.Editora;

public class DvdFindDTO {
	private Long id;
	
	private String nome;
	
	private Editora editora;
	
	private Integer quantidadeEmEstoque;
	
	public DvdFindDTO() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	@JsonIgnore
	public Integer getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}

	public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}
}
