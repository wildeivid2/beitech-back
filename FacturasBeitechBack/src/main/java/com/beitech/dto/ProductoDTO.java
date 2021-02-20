package com.beitech.dto;

import java.io.Serializable;

public class ProductoDTO implements Serializable {
	
	private Integer id;
	private String nombre;
	private String descripcion;
	private Double precio;
	
	private static final long serialVersionUID = 1L;
	

	public ProductoDTO() {
		super();
	}


	public ProductoDTO(Integer id, String nombre, String descripcion, Double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
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


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	
}
