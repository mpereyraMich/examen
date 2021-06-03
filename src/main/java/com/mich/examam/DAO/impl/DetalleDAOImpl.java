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
import com.mich.examam.model.Detalle;
import com.mich.examam.model.Pedido;

@Repository
public class DetalleDAOImpl implements DetalleDAO {

	@Autowired private JdbcTemplate jdbcTemplate;
	
	@Override
	public void save(Detalle detalle) {
		StringBuilder sql = new StringBuilder();

		sql.append("insert into ").append(TABLE);
		sql.append("( ");
		sql.append(COLUMN_ID);
		sql.append(",").append(COLUMN_PEDIDO);
		sql.append(",").append(COLUMN_SKU);
		sql.append(",").append(COLUMN_PRICE);
		sql.append(",").append(COLUMN_AMOUNT);
		sql.append(") values (?,?,?,?,?)");

		OracleSequenceMaxValueIncrementer seq = new OracleSequenceMaxValueIncrementer();
		seq.setDataSource(jdbcTemplate.getDataSource());
		seq.setIncrementerName(SEQUENCE);
		
		detalle.setId(seq.nextLongValue());
		
		List<Object> params = new ArrayList<>();
		params.add(detalle.getId());
		params.add(detalle.getPedido().getId());
		params.add(detalle.getSku());
		params.add(detalle.getPrice());
		params.add(detalle.getAmount());
		
		jdbcTemplate.update(sql.toString(),params.toArray());

	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Detalle> getByPedido(Pedido pedido) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(TABLE);
		sql.append(" where  ").append(COLUMN_PEDIDO).append(" = ? ");
		
		List<Object> params = new ArrayList<>();
		params.add(pedido.getId());
		
		return jdbcTemplate.query(sql.toString(),params.toArray() ,new DetalleMapper());
	}
	
	private final class DetalleMapper implements RowMapper<Detalle> {

		@Override
		public Detalle mapRow(ResultSet rs, int rowNum) throws SQLException {
			Detalle model = new Detalle();
			
			model.setId(rs.getLong(COLUMN_ID));
			Pedido pedido = new Pedido();
			pedido.setId(rs.getLong(COLUMN_PEDIDO));
			model.setPedido(pedido);
			model.setAmount(rs.getDouble(COLUMN_AMOUNT));
			model.setPrice(rs.getDouble(COLUMN_PRICE));
			model.setSku(rs.getString(COLUMN_SKU));
			
			return model;
		}
		
	}

}
