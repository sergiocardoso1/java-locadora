package com.srg.locadora.services;

import java.util.List;

import com.srg.locadora.domain.Editora;

public interface EditoraService {
	
	public Editora findId(Long id);

	public List<Editora> findAll();

	public Editora insert(Editora obj);

	public Editora update(Editora obj);

	public void delete(Long id);

}
