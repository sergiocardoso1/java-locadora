package com.srg.locadora.services;

import java.util.List;

import com.srg.locadora.domain.Cliente;
import com.srg.locadora.dto.ClienteDTO;
import com.srg.locadora.dto.ClienteNewDTO;

public interface ClienteService {
	
	public Cliente findId(Long id);
	
	public List<Cliente> findAll();
	
	public Cliente insert(Cliente obj);
	
	public Cliente update(Cliente obj);

	public void delete(Long id);
	
	public Cliente fromDTO (ClienteDTO objDto);
	
	public Cliente fromDTO(ClienteNewDTO objDTO);

}
