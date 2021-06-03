package com.mich.examam.model;

import java.util.Date;
import java.util.List;

public class Pedido {

	private Long id;
	private Double total;
	private Date dateSale;
	
	private List<Detalle> listDetalle;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Double total) {
		this.total = total;
	}
	/**
	 * @return the dateSale
	 */
	public Date getDateSale() {
		return dateSale;
	}
	/**
	 * @param dateSale the dateSale to set
	 */
	public void setDateSale(Date dateSale) {
		this.dateSale = dateSale;
	}
	/**
	 * @return the listDetalle
	 */
	public List<Detalle> getListDetalle() {
		return listDetalle;
	}
	/**
	 * @param listDetalle the listDetalle to set
	 */
	public void setListDetalle(List<Detalle> listDetalle) {
		this.listDetalle = listDetalle;
	}
	
	
}
