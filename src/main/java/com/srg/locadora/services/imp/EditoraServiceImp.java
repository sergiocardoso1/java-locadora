package com.srg.locadora.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.srg.locadora.domain.Editora;
import com.srg.locadora.repositories.EditoraRepository;
import com.srg.locadora.services.EditoraService;
import com.srg.locadora.services.exceptions.DataIntegrityException;
import com.srg.locadora.services.exceptions.ObjectNotFoundException;

@Service
public class EditoraServiceImp implements EditoraService{
	
	@Autowired
	private EditoraRepository repository;

	public Editora findId(Long id) {
		Optional<Editora> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Editora.class.getName()));
	}

	public List<Editora> findAll() {
		return repository.findAll();
	}

	public Editora insert(Editora obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Editora update(Editora obj) {
		Editora newObj = findId(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void delete(Long id) {
		findId(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma editora que possui dvds!");
		}
	}

	public void updateData(Editora newObj, Editora obj) {
		newObj.setNome(obj.getNome());
	}
}
