package com.mich.examam.DAO;

import java.util.List;

import com.mich.examam.model.Pedido;

public interface PedidoDAO {

	String TABLE ="PEDIDOS_W";
	String COLUMN_ID = "ID";
	String COLUMN_TOTAL = "TOTAL";
	String COLUMN_DATE_SALE = "DATE_SALE";
	
	String SEQUENCE = "PEDIDO_SEQ";
	
	void save(Pedido pedido);
	
	List<Pedido> getAll();
	
	
	
}
