package com.srg.locadora.services;

import com.srg.locadora.domain.Pedido;

public interface PedidoService {
	
	public Pedido findId(Long id);
	
	public Pedido insert(Pedido obj);

}
