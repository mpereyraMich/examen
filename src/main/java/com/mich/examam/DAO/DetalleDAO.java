package com.mich.examam.DAO;

import java.util.List;

import com.mich.examam.model.Detalle;
import com.mich.examam.model.Pedido;

public interface DetalleDAO {

	String TABLE ="PEDIDOS_DETALLE_W";
	String COLUMN_ID = "ID";
	String COLUMN_PEDIDO = "ID_PEDIDO";
	String COLUMN_SKU = "SKU";
	String COLUMN_AMOUNT = "AMOUT";
	String COLUMN_PRICE = "PRICE";
	
	String SEQUENCE = "PEDIDO_DETALLE_SEQ";
	
	void save(Detalle detalle);
	
	List<Detalle> getByPedido(Pedido pedido);
	
}
