package com.mich.examam.service;

import java.util.List;

import com.mich.examam.model.Pedido;

public interface PedidoService {

	void save(Pedido pedido);
	
	List<Pedido> getAll();
	
}
