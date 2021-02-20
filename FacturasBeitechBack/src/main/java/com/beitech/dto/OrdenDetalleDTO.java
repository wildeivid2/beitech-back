package com.beitech.dto;

import java.io.Serializable;

public class OrdenDetalleDTO implements Serializable {

	private Integer id;
	private OrdenDTO orden;
	private ProductoDTO producto;
	private Double precio;
	private int cantidad;
	
	
	private static final long serialVersionUID = 1L;


	public OrdenDetalleDTO() {
		super();
	}


	public OrdenDetalleDTO(Integer id, OrdenDTO orden, ProductoDTO producto, Double precio, int cantidad) {
		super();
		this.id = id;
		this.orden = orden;
		this.producto = producto;
		this.precio = precio;
		this.cantidad = cantidad;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public OrdenDTO getOrden() {
		return orden;
	}


	public void setOrden(OrdenDTO orden) {
		this.orden = orden;
	}


	public ProductoDTO getProducto() {
		return producto;
	}


	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
