package com.beitech.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "customer", indexes = {
		@Index(name = "INDEX_CUSTOMER_EMAIL", columnList = "email", unique = true)
})
public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Integer id;
	
	@Column(name = "name", length = 191)
	@Size(min = 3, max = 191)
	private String nombre;
	
	@Email
	@NotBlank
	@Size(max = 191)
	@Column(name = "email", length = 191, unique = true)
	private String correoElectronico;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	@JoinColumn(name = "customer_id")
	private Collection<ProductoCliente> productosCliente;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "customer_id")
	private Collection<Orden> ordenes;
	
	
	private static final long serialVersionUID = 1L;


	public Cliente() {
		super();
	}


	public Cliente(Integer id, String nombre, String correoElectronico, Collection<ProductoCliente> productosCliente, Collection<Orden> ordenes) {
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


	public Collection<ProductoCliente> getProductosCliente() {
		return productosCliente;
	}


	public void setProductosCliente(Collection<ProductoCliente> productosCliente) {
		this.productosCliente = productosCliente;
	}


	public Collection<Orden> getOrdenes() {
		return ordenes;
	}


	public void setOrdenes(Collection<Orden> ordenes) {
		this.ordenes = ordenes;
	}
	
}
