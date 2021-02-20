package com.beitech.services;

import java.util.Collection;

import com.beitech.dto.ProductoDTO;
import com.beitech.models.Cliente;

public interface IProductoService {
	
	public Collection<ProductoDTO> consultarProductos();
	
	public Collection<ProductoDTO> consultarProductosPorCliente(Cliente cliente);
	
}
