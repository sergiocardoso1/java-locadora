package com.srg.locadora.services;

import java.util.List;

import com.srg.locadora.domain.Dvd;
import com.srg.locadora.dto.DvdDTO;
import com.srg.locadora.dto.DvdNewDTO;

public interface DvdService {
	
	public Dvd findId(Long id);

	public List<Dvd> findAll();
	
	public Dvd insert(Dvd obj);
	
	public Dvd update(Dvd obj);
	
	public void delete(Long id);
	
	public Dvd fromDTO(DvdDTO objDTO);
	
	public Dvd fromDTO(DvdNewDTO objDTO);
}
