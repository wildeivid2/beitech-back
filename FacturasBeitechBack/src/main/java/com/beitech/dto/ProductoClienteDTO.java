package com.beitech.dto;

import java.io.Serializable;

public class ProductoClienteDTO implements Serializable {
	
	private Integer id;
	private ClienteDTO cliente;
	private ProductoDTO producto;
	
	private static final long serialVersionUID = 1L;

	public ProductoClienteDTO() {
		super();
	}

	public ProductoClienteDTO(ClienteDTO cliente, ProductoDTO producto) {
		super();
		this.cliente = cliente;
		this.producto = producto;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public ProductoDTO getProducto() {
		return producto;
	}

	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}
	
}
