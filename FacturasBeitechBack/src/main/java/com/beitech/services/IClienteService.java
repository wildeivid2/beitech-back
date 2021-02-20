package com.beitech.services;

import java.util.Collection;

import com.beitech.dto.ClienteDTO;
import com.beitech.dto.ProductoDTO;
import com.beitech.models.Cliente;

public interface IClienteService {
	
	public Collection<ClienteDTO> listarClientes() throws Throwable;
	
	public ClienteDTO consultarClientePorId(Integer id);
	
	public ClienteDTO consultarCliente(Cliente cliente);
	
	public Collection<ProductoDTO> consultarProductosPorClienteId(Integer cliente);

}
