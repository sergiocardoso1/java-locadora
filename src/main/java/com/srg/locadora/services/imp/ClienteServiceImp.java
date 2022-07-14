package com.srg.locadora.services.imp;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.srg.locadora.domain.Cidade;
import com.srg.locadora.domain.Cliente;
import com.srg.locadora.domain.Endereco;
import com.srg.locadora.dto.ClienteDTO;
import com.srg.locadora.dto.ClienteNewDTO;
import com.srg.locadora.repositories.ClienteRepository;
import com.srg.locadora.repositories.EnderecoRepository;
import com.srg.locadora.services.ClienteService;
import com.srg.locadora.services.exceptions.DataIntegrityException;
import com.srg.locadora.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteServiceImp implements ClienteService{

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente findId(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public List<Cliente> findAll(){
		return repository.findAll();
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repository.save(obj);
		enderecoRepository.save(obj.getEndereco());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = findId(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void delete(Long id) {
		findId(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos!");
		}

	}
	
	public Cliente fromDTO (ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getCpf(), objDto.getEmail());
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cidade cidade = new Cidade(objDTO.getCidadeId(), null, null);
		Cliente cliente = new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getEmail());
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cliente, cidade);
		cliente.setEndereco(end);
		return cliente;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setEmail(obj.getEmail());
		newObj.setNome(obj.getNome());
		newObj.setCpf(obj.getCpf());
	}
}
