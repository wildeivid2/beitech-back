package com.beitech.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Orden implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Cliente cliente;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "creation_date")
	private Date fechaCreacion;
	
	@Column(name = "delivery_address", length = 191)
	private String direccionEnvio;
	
	@Column(precision = 10, scale = 2)
	private Double total;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private Collection<OrdenDetalle> detallesOrden;
	
	
	private static final long serialVersionUID = 1L;


	public Orden() {
		super();
	}


	public Orden(Integer id, Cliente cliente, Date fechaCreacion, String direccionEnvio, Double total) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.fechaCreacion = fechaCreacion;
		this.direccionEnvio = direccionEnvio;
		this.total = total;
	}
	
	@PrePersist
	public void prePersist() {
		this.setFechaCreacion(new Date());
		
		if(this.getTotal() == null) {
			Double totalCalculado = Double.valueOf(0);
			for(OrdenDetalle ordenDetalle : this.getDetallesOrden()) {
				totalCalculado = totalCalculado + (ordenDetalle.getCantidad() * ordenDetalle.getPrecio());
			}
			this.setTotal(totalCalculado);
		}
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
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


	public Collection<OrdenDetalle> getDetallesOrden() {
		return detallesOrden;
	}


	public void setDetallesOrden(Collection<OrdenDetalle> detallesOrden) {
		this.detallesOrden = detallesOrden;
	}

}
