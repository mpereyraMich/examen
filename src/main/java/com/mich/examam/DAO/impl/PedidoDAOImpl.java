package com.mich.examam.DAO.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.stereotype.Repository;

import com.mich.examam.DAO.DetalleDAO;
import com.mich.examam.DAO.PedidoDAO;
import com.mich.examam.model.Pedido;

@Repository
public class PedidoDAOImpl implements PedidoDAO {

	@Autowired private JdbcTemplate jdbcTemplate;
	
	@Autowired private DetalleDAO detalleDAO;
	
	@Override
	public void save(Pedido pedido) {
		StringBuilder sql = new StringBuilder();

		sql.append("insert into ").append(TABLE);
		sql.append("( ");
		sql.append(COLUMN_ID);
		sql.append(",").append(COLUMN_TOTAL);
		sql.append(",").append(COLUMN_DATE_SALE);
		sql.append(") values (?,?,?)");

		OracleSequenceMaxValueIncrementer seq = new OracleSequenceMaxValueIncrementer();
		seq.setDataSource(jdbcTemplate.getDataSource());
		seq.setIncrementerName(SEQUENCE);
		
		pedido.setId(seq.nextLongValue());
		
		List<Object> params = new ArrayList<>();
		params.add(pedido.getId());
		params.add(pedido.getTotal());
		params.add(pedido.getDateSale());
		
		jdbcTemplate.update(sql.toString(),params.toArray());
		
	}

	@Override
	public List<Pedido> getAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(TABLE);
		
		List<Pedido> list = new ArrayList<>();
		
		list = jdbcTemplate.query(sql.toString(), new PedidoMapper());
		
		for (Pedido pedido : list) {
			pedido.setListDetalle(detalleDAO.getByPedido(pedido));
		}
		
		return list;
	}
	
	

	private final class PedidoMapper implements RowMapper<Pedido> {

		@Override
		public Pedido mapRow(ResultSet rs, int rowNum) throws SQLException {
			Pedido model = new Pedido();
			
			model.setId(rs.getLong(COLUMN_ID));
			model.setTotal(rs.getDouble(COLUMN_TOTAL));
			model.setDateSale(rs.getDate(COLUMN_DATE_SALE));
			
			return model;
		}
		
	}

}
