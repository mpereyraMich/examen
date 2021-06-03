package com.mich.examam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mich.examam.DAO.DetalleDAO;
import com.mich.examam.DAO.PedidoDAO;
import com.mich.examam.model.Detalle;
import com.mich.examam.model.Pedido;
import com.mich.examam.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired private PedidoDAO pedidoDAO;
	@Autowired private DetalleDAO detalleDAO;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, rollbackFor = Exception.class)
	public void save(Pedido pedido) {
		
		pedidoDAO.save(pedido);
		
		for (Detalle detalle : pedido.getListDetalle()) {
			detalle.setPedido(pedido);
			detalleDAO.save(detalle);
		}

	}

	@Override
	public List<Pedido> getAll() {
		
		return pedidoDAO.getAll();
	}

}
