package com.beitech.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class OrdenDTO implements Serializable {
	
	private Integer id;
	private ClienteDTO cliente;
	private Date fechaCreacion;
	private String direccionEnvio;
	private Double total;
	private Collection<OrdenDetalleDTO> detallesOrden;
	
	
	private static final long serialVersionUID = 1L;


	public OrdenDTO() {
		super();
	}


	public OrdenDTO(Integer id, ClienteDTO cliente, Date fechaCreacion, String direccionEnvio, Double total, Collection<OrdenDetalleDTO> detallesOrden) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.fechaCreacion = fechaCreacion;
		this.direccionEnvio = direccionEnvio;
		this.total = total;
		this.detallesOrden = detallesOrden;
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


	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public String getDireccionEnvio() {
		return direccionEnvio;
	}


	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}


	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
	}


	public Collection<OrdenDetalleDTO> getDetallesOrden() {
		return detallesOrden;
	}


	public void setDetallesOrden(Collection<OrdenDetalleDTO> detallesOrden) {
		this.detallesOrden = detallesOrden;
	}

}
