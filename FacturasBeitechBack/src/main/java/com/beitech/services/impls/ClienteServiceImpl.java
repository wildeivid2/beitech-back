package com.beitech.services.impls;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.beitech.dao.IClienteDAO;
import com.beitech.dto.ClienteDTO;
import com.beitech.dto.OrdenDTO;
import com.beitech.dto.OrdenDetalleDTO;
import com.beitech.dto.ProductoClienteDTO;
import com.beitech.dto.ProductoDTO;
import com.beitech.models.Cliente;
import com.beitech.models.Orden;
import com.beitech.models.OrdenDetalle;
import com.beitech.models.Producto;
import com.beitech.models.ProductoCliente;
import com.beitech.services.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	IClienteDAO iClienteDAO;
	
	
	@Override
	public Collection<ClienteDTO> listarClientes() throws Throwable {
		Collection<ClienteDTO> clientes = null;
		Collection<Cliente> clientesConsultados = (Collection<Cliente>) this.iClienteDAO.findAll(); 
		
		if(clientesConsultados != null && !clientesConsultados.isEmpty()) {
			clientes = new ArrayList<>();
			
			for(Cliente cliente : clientesConsultados) {
				clientes.add(this.construirClienteDTO(cliente));
			}
		}else {
			throw new Throwable("No hay clientes registrados..!");
		}
		
		return clientes;
	}

	@Override
	public ClienteDTO consultarClientePorId(Integer id) {
		ClienteDTO clienteDTO = null;
		Cliente cliente = this.iClienteDAO.findById(id).orElse(null);
		
		if(cliente != null) {
			clienteDTO = this.construirClienteDTO(cliente);
		}
		
		return clienteDTO;
	}

	@Override
	public ClienteDTO consultarCliente(Cliente cliente) {
		ClienteDTO clienteDTO = null;
		Cliente clienteConsultado = this.iClienteDAO.findById(cliente.getId()).orElse(null);
		
		if(clienteConsultado != null) {
			clienteDTO = this.construirClienteDTO(clienteConsultado);
		}
		
		return clienteDTO;
	}

	@Override
	public Collection<ProductoDTO> consultarProductosPorClienteId(Integer id) {
		Collection<ProductoDTO> productosConsultados = null;
		
		
		Cliente clienteConsultado = this.iClienteDAO.findById(id).orElse(null);
		
		if(clienteConsultado != null) {
			productosConsultados = new ArrayList<>();
			
			for(ProductoCliente productoCliente : clienteConsultado.getProductosCliente()) {
				productosConsultados.add(this.construirProductoDTO(productoCliente.getProducto()));
			}
		}
		
		
		return productosConsultados;
	}
	
	private ClienteDTO construirClienteDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		
		clienteDTO.setId(cliente.getId());
		clienteDTO.setNombre(cliente.getNombre());
		clienteDTO.setCorreoElectronico(cliente.getCorreoElectronico());
		clienteDTO.setProductosCliente(this.construirProductoClienteDTO(cliente.getProductosCliente()));
		clienteDTO.setOrdenes(this.construirOrdenDTO(cliente.getOrdenes()));
		
		return clienteDTO;
	}
	
	private ProductoDTO construirProductoDTO(Producto producto) {
		ProductoDTO productoDTO = new ProductoDTO();
		
		productoDTO.setId(producto.getId());
		productoDTO.setNombre(producto.getNombre());
		productoDTO.setDescripcion(producto.getDescripcion());
		productoDTO.setPrecio(producto.getPrecio());
		
		return productoDTO;
	}
	
	private Collection<OrdenDTO> construirOrdenDTO(Collection<Orden> ordenes) {
		Collection<OrdenDTO> ordenesDTO = new ArrayList<>();
		
		for(Orden orden : ordenes) {
			OrdenDTO ordenDTO = new OrdenDTO();
			
			ordenDTO.setId(orden.getId());
			ordenDTO.setDireccionEnvio(orden.getDireccionEnvio());
			ordenDTO.setFechaCreacion(orden.getFechaCreacion());
			ordenDTO.setTotal(orden.getTotal());
			ordenDTO.setDetallesOrden(this.construirOrdenDetalleDTO(orden.getDetallesOrden()));
			
			ordenesDTO.add(ordenDTO);
		}
		
		
		return ordenesDTO;
	}
	
	private Collection<ProductoClienteDTO> construirProductoClienteDTO(Collection<ProductoCliente> productoCliente) {
		Collection<ProductoClienteDTO> productosClienteDTO = new ArrayList<>();
		
		for(ProductoCliente producto : productoCliente) {
			ProductoClienteDTO productoClienteDTO = new ProductoClienteDTO();
			productoClienteDTO.setId(producto.getId());
			productoClienteDTO.setProducto(this.construirProductoDTO(producto.getProducto()));
			
			productosClienteDTO.add(productoClienteDTO);
		}
		
		
		
		return productosClienteDTO;
	}
	
	private Collection<OrdenDetalleDTO> construirOrdenDetalleDTO(Collection<OrdenDetalle> ordenes) {
		Collection<OrdenDetalleDTO> ordenesDTO = null;
		
		if(ordenes != null && !ordenes.isEmpty()) {
			ordenesDTO = new ArrayList<>();
			for(OrdenDetalle ordenDetalle : ordenes) {
				OrdenDetalleDTO ordenDetalleDTO = new OrdenDetalleDTO();
				ordenDetalleDTO.setId(ordenDetalle.getId());
				ordenDetalleDTO.setCantidad(ordenDetalle.getCantidad());
				ordenDetalleDTO.setPrecio(ordenDetalle.getPrecio());
				ordenDetalleDTO.setProducto(this.construirProductoDTO(ordenDetalle.getProducto()));
				
				ordenesDTO.add(ordenDetalleDTO);				
			}
		}
		
		return ordenesDTO;
	}

}
