package com.beitech.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrdenDetalle implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_detail_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orden orden;
	
	@OneToOne
	@JoinColumn(name = "product_id")
	private Producto producto;
	
	@Column(name = "price", precision = 10, scale = 2)
	private Double precio;
	
	@Column(name = "quantity")
	private int cantidad;
	
	
	private static final long serialVersionUID = 1L;


	public OrdenDetalle() {
		super();
	}


	public OrdenDetalle(Integer id, Orden orden, Producto producto, Double precio, int cantidad) {
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


	public Orden getOrden() {
		return orden;
	}


	public void setOrden(Orden orden) {
		this.orden = orden;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
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
