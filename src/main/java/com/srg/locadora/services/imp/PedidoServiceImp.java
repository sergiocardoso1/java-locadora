package com.srg.locadora.services.imp;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srg.locadora.domain.ItemPedido;
import com.srg.locadora.domain.Pedido;
import com.srg.locadora.enums.EstadoPagamento;
import com.srg.locadora.repositories.ItemPedidoRepository;
import com.srg.locadora.repositories.PagamentoRepository;
import com.srg.locadora.repositories.PedidoRepository;
import com.srg.locadora.services.ClienteService;
import com.srg.locadora.services.DvdService;
import com.srg.locadora.services.PedidoService;
import com.srg.locadora.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoServiceImp implements PedidoService{
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private DvdService dvdService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public Pedido findId(Long id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.findId(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setDvd(dvdService.findId(ip.getDvd().getId()));
			if(ip.getDvd().getQuantidadeEmEstoque() >= 1) {
				ip.alterarDvd().setQuantidadeEmEstoque(ip.getDvd().getQuantidadeEmEstoque()-1);
			}else {
				return null;
			}
			
			ip.setPedido(obj);
			
			
			
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;

	}
	
	/*public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.findID(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.findID(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendEmail(obj);
		return obj;
	}*/

}
