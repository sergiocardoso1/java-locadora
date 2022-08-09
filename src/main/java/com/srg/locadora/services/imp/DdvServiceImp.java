package com.srg.locadora.services.imp;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srg.locadora.domain.Dvd;
import com.srg.locadora.domain.Editora;
import com.srg.locadora.dto.DvdDTO;
import com.srg.locadora.dto.DvdNewDTO;
import com.srg.locadora.repositories.DvdRepository;
import com.srg.locadora.repositories.EditoraRepository;
import com.srg.locadora.services.DvdService;
import com.srg.locadora.services.EditoraService;
import com.srg.locadora.services.exceptions.ObjectNotFoundException;

@Service
public class DdvServiceImp implements DvdService{
	@Autowired
	private DvdRepository repository;
	
	@Autowired
	private EditoraService editoraService;
	
	@Autowired
	private EditoraRepository editoraRepository;
	
	@Transactional
	public Dvd findId(Long id) {
		Optional<Dvd> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Editora.class.getName()));
	}

	@Transactional
	public List<Dvd> findAll() {
		return repository.findAll();
	}
	
	public Dvd insert(Dvd obj) {
		obj.setId(null);
		obj = repository.save(obj);
		editoraRepository.save(obj.getEditora());
		return obj;
	}
	
	public Dvd update(Dvd obj) {
		Dvd newObj = findId(obj.getId());
		updateData(newObj, obj);
		repository.save(newObj);
		return newObj;
		
	}
	
	public void delete(Long id) {
		findId(id);
		repository.deleteById(id);
	}
	
	public Dvd fromDTO(DvdDTO objDTO) {
		Dvd obj = new Dvd(objDTO.getId(), objDTO.getNome());
		obj.setEditora(editoraService.findId(objDTO.getIdEditora()));
		return obj;
	}
	
	public Dvd fromDTO(DvdNewDTO objDTO) {
		Dvd obj = new Dvd();
		obj.setNome(objDTO.getNome());
		obj.setQuantidadeEmEstoque(objDTO.getQuantidadeEmEstoque());
		obj.setEditora(editoraService.findId(objDTO.getIdEditora()));
		return obj;
	}
	
	public void updateData(Dvd newObj, Dvd obj) {
		newObj.setNome(obj.getNome());
		newObj.setEditora(obj.getEditora());
	}
	
	
}
