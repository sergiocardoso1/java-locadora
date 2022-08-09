package com.srg.locadora.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srg.locadora.domain.ItemPedido;
import com.srg.locadora.domain.Pedido;
import com.srg.locadora.enums.EstadoPagamento;
import com.srg.locadora.repositories.ItemPedidoRepository;
import com.srg.locadora.repositories.PedidoRepository;
import com.srg.locadora.services.DevolucaoService;
import com.srg.locadora.services.PedidoService;

@Service
public class DevolucaoServiceImp implements DevolucaoService{

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public void insert(Long id) {
		Pedido obj = pedidoService.findId(id);
		if(obj.getPagamento().getEstado() == EstadoPagamento.PENDENTE) {
			obj.getPagamento().setEstado(EstadoPagamento.QUITADO);
			for (ItemPedido ip : obj.getItens()) {
				ip.alterarDvd().setQuantidadeEmEstoque(ip.getDvd().getQuantidadeEmEstoque()+1);
				
			}
			pedidoRepository.save(obj);
			itemPedidoRepository.saveAll(obj.getItens());
		}
	
	}
}
