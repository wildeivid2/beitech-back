package com.beitech.dto;

import java.io.Serializable;
import java.util.Collection;

public class ClienteDTO implements Serializable {

	private Integer id;
	private String nombre;
	private String correoElectronico;
	private Collection<ProductoClienteDTO> productosCliente;
	private Collection<OrdenDTO> ordenes;
	
	
	private static final long serialVersionUID = 1L;


	public ClienteDTO() {
		super();
	}


	public ClienteDTO(Integer id, String nombre, String correoElectronico, Collection<ProductoClienteDTO> productosCliente, Collection<OrdenDTO> ordenes) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.correoElectronico = correoElectronico;
		this.productosCliente = productosCliente;
		this.ordenes = ordenes;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCorreoElectronico() {
		return correoElectronico;
	}


	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}


	public Collection<ProductoClienteDTO> getProductosCliente() {
		return productosCliente;
	}


	public void setProductosCliente(Collection<ProductoClienteDTO> productosCliente) {
		this.productosCliente = productosCliente;
	}


	public Collection<OrdenDTO> getOrdenes() {
		return ordenes;
	}


	public void setOrdenes(Collection<OrdenDTO> ordenes) {
		this.ordenes = ordenes;
	}
	
}
