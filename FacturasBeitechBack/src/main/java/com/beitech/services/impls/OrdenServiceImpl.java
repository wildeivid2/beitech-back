package com.beitech.services.impls;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.beitech.dao.IClienteDAO;
import com.beitech.dao.IOrdenDAO;
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
import com.beitech.services.IOrdenService;
import com.beitech.utils.DateUtils;

@Service
public class OrdenServiceImpl implements IOrdenService {
	
	@Autowired
	private IClienteDAO iClienteDAO;
	
	@Autowired IOrdenDAO iOrdenDAO;

	@Override
	public Collection<OrdenDTO> listarOrdenes() {
		Collection<OrdenDTO> ordenesDTO = null;
		Collection<Orden> ordenesConsultadas = (Collection<Orden>) this.iOrdenDAO.findAll();
		
		if(ordenesConsultadas != null && !ordenesConsultadas.isEmpty()) {
			ordenesDTO = new ArrayList<>();
			for(Orden orden : ordenesConsultadas) {
				ordenesDTO.add(this.construirOrdenDTO(orden));
			}
		}
		
		return ordenesDTO;
	}

	@Override
	public Collection<OrdenDTO> listarOrdenesPorCliente(Integer id) throws Throwable {
		Collection<OrdenDTO> ordenesDTO = null;
		Cliente clienteConsultado = this.iClienteDAO.findById(id).orElse(null);
		
		if(clienteConsultado != null && !clienteConsultado.getOrdenes().isEmpty()) {
			ordenesDTO = new ArrayList<>();
			
			for(Orden orden : clienteConsultado.getOrdenes()) {
				ordenesDTO.add(this.construirOrdenDTO(orden));
			}	
			
		}else {
			throw new Throwable("Cliente ".concat(id.toString()).concat(" no existe."));
		}
		
		return ordenesDTO;
	}

	@Override
	public Collection<OrdenDTO> listarOrdenesDelUltimoMesPorCliente(Integer id) throws ParseException {
		Collection<OrdenDTO> ordenesDTO = null;
		Cliente cliente = new Cliente();
		cliente.setId(id);
		
		if(cliente != null && cliente.getId() != null) {
			Cliente clienteConsultado = this.iClienteDAO.findById(cliente.getId()).orElse(null);
			if(clienteConsultado != null) { 
				Calendar fechaActual = Calendar.getInstance();
				String year = String.valueOf(fechaActual.get(Calendar.YEAR));
				String mes = this.formatoValidoMes(String.valueOf(fechaActual.get(Calendar.MONTH)+1));
				String dia = "01";
				Date fechaInicial = DateUtils.asDate(LocalDate.parse(year.concat("-").concat(mes).concat("-").concat(dia)));
				Date fechaFin = DateUtils.asDate(LocalDate.now()); 
				
				Collection<Orden> ordenesConsultadas = this.iOrdenDAO.findAllByClienteAndFechaCreacionBetween(cliente, fechaInicial, fechaFin);
				ordenesDTO = new ArrayList<>();
				
				for(Orden orden : ordenesConsultadas) {
					ordenesDTO.add(this.construirOrdenDTO(orden));
				}
			}
		}
		
		return ordenesDTO;
	}

	@Override
	public OrdenDTO guardarOrden(Orden orden) throws Throwable {
		Orden ordenGuardada = null;
		Cliente cliente = this.iClienteDAO.findById(orden.getCliente().getId()).orElse(null);
		
		if(cliente != null) {
			orden.setCliente(cliente);
			
			if(this.validarCantidadDeProductos(orden)) {
				ordenGuardada = this.iOrdenDAO.save(orden);
			}else {
				throw new Throwable("La orden tiene más de 5 productos.");
			}
			
		} else {
			throw new Throwable("El cliente no existe, no se puede guardar la orden.");
		}
		
		return this.construirOrdenDTO(ordenGuardada);
	}
	
	private OrdenDTO construirOrdenDTO(Orden orden) {
		OrdenDTO ordenDTO = new OrdenDTO();
		
		ordenDTO.setId(orden.getId());
		ordenDTO.setDireccionEnvio(orden.getDireccionEnvio());
		ordenDTO.setFechaCreacion(orden.getFechaCreacion());
		ordenDTO.setTotal(orden.getTotal());
		ordenDTO.setCliente(this.construirClienteDTO(orden.getCliente()));
		ordenDTO.setDetallesOrden(this.construirOrdenDetalleDTO(orden.getDetallesOrden()));
		
		return ordenDTO;
	}
	
	private ClienteDTO construirClienteDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		
		clienteDTO.setId(cliente.getId());
		clienteDTO.setNombre(cliente.getNombre());
		clienteDTO.setCorreoElectronico(cliente.getCorreoElectronico());
		
		return clienteDTO;
	}
	
	private ProductoDTO construirProductoDTO(Producto producto) {
		ProductoDTO productoDTO = new ProductoDTO();
		
		productoDTO.setId(producto.getId());
		productoDTO.setNombre(producto.getNombre());
		productoDTO.setPrecio(producto.getPrecio());
		productoDTO.setDescripcion(producto.getDescripcion());
		
		return productoDTO;

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
	
	private Collection<ProductoClienteDTO> construirProductoClienteDTO(Collection<ProductoCliente> productoCliente) {
		Collection<ProductoClienteDTO> productosCliente = new ArrayList<>();
		
		for(ProductoCliente producto : productoCliente) {
			ProductoClienteDTO productoClienteDTO = new ProductoClienteDTO();
			productoClienteDTO.setId(producto.getId());
			productoClienteDTO.setProducto(this.construirProductoDTO(producto.getProducto()));
			
			productosCliente.add(productoClienteDTO);
		}
		
		return productosCliente;
	}
	
	private boolean validarCantidadDeProductos(Orden orden) {
		boolean resultado = false;
		
		if(orden != null && orden.getDetallesOrden() != null && !orden.getDetallesOrden().isEmpty()) {
			
			resultado = true;
			int cantidadProducto = 0;
			
			for(OrdenDetalle ordenDetalle : orden.getDetallesOrden()) {
				cantidadProducto += ordenDetalle.getCantidad();
				
				if(cantidadProducto > 5) {
					resultado = false;
					break;
				}
			}
		}
		
		return resultado;
	}
	
	private String formatoValidoMes(String mes) {
		if(mes.length() == 1) {
			return "0".concat(mes);
		}
		return mes;
	}

}
