package com.srg.locadora.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.srg.locadora.services.DevolucaoService;

@RestController
@RequestMapping(value = "/devolucoes")
public class DevolucaoControler {
	
	@Autowired
	private DevolucaoService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@PathVariable Long id) {
		service.insert(id);
		return ResponseEntity.noContent().build();
	}
	
	
	

}
