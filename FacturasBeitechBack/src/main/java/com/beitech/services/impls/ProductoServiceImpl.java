package com.beitech.services.impls;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beitech.dao.IClienteDAO;
import com.beitech.dao.IProductoDAO;
import com.beitech.dto.ProductoDTO;
import com.beitech.models.Cliente;
import com.beitech.models.Producto;
import com.beitech.models.ProductoCliente;
import com.beitech.services.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	private IClienteDAO iClienteDAO;
	
	@Autowired
	private IProductoDAO iProductoDAO;
	
	
	@Override
	public Collection<ProductoDTO> consultarProductos() {
		Collection<ProductoDTO> productos = null;
		Collection<Producto> productosConsultados = (Collection<Producto>) this.iProductoDAO.findAll();
		
		if(productosConsultados != null) {
			for(Producto producto : productosConsultados) {
				productos = new ArrayList<>();
				productos.add(this.construirProductoDTO(producto));
			}
		}
		
		return productos;
	}

	@Override
	public Collection<ProductoDTO> consultarProductosPorCliente(Cliente cliente) {
		Collection<ProductoDTO> productos = null;
		
		if(cliente != null && cliente.getId() != null) {
			Cliente clienteConsultado = this.iClienteDAO.findById(cliente.getId()).orElse(null);
			
			if(clienteConsultado != null && !clienteConsultado.getProductosCliente().isEmpty()) {
				productos = new ArrayList<>();
				for(ProductoCliente productoCliente : clienteConsultado.getProductosCliente()) {
					productos.add(this.construirProductoDTO(productoCliente.getProducto()));
				}
			}
		}
		
		return productos;
	}
	
	private ProductoDTO construirProductoDTO(Producto producto) {
		ProductoDTO productoDTO = new ProductoDTO();
		
		productoDTO.setId(producto.getId());
		productoDTO.setNombre(producto.getNombre());
		productoDTO.setPrecio(producto.getPrecio());
		productoDTO.setDescripcion(producto.getDescripcion());
		
		return productoDTO;

	}

}
