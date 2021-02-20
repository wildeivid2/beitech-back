package com.beitech.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beitech.dto.ClienteDTO;
import com.beitech.dto.ProductoDTO;
import com.beitech.services.IClienteService;

@RestController
@CrossOrigin({"*"})
@RequestMapping(value = "/api/clientes")
public class ClienteRestController {
	
	@Autowired
	IClienteService iClienteService;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(ClienteRestController.class);
	
	@GetMapping("/listar")
	public ResponseEntity<?> listarCliente(){
		Collection<ClienteDTO> clientes = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			clientes = this.iClienteService.listarClientes();
		}catch(DataAccessException e) {
			LOGGER.error("[ERROR] [ClienteRestController.listarCliente()] [problemas al consultar todos los clientes] ".concat(e.getCause().toString()));
			response.put("mensaje", "Error consultado los clientes");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Throwable e) {
			LOGGER.info("[ERROR] [ClienteRestController.listarCliente()] [no hay clientes registrados] ".concat(e.getMessage()));
			response.put("mensaje", "Error consultado los clientes");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<Collection<ClienteDTO>>(clientes, HttpStatus.OK);
	}
	
	@GetMapping("/consultar/{id}")
	public ResponseEntity<?> consultarCliente(@PathVariable Integer id) {
		ClienteDTO cliente = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			cliente = this.iClienteService.consultarClientePorId(id);
		}catch(DataAccessException e) {
			LOGGER.error("[ERROR] [ClienteRestController.consultarCliente(id)] [problemas al consultar un cliente por id] ".concat(e.getCause().toString()));
			response.put("mensaje", "Error consultado los clientes");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(cliente == null) {
			LOGGER.info("[ERROR] [ClienteRestController.consultarCliente(id)] [el cliente no existe] ");
			response.put("mensaje", "El cliente ".concat(id.toString()).concat(" no existe."));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		
		return new ResponseEntity<ClienteDTO>(cliente, HttpStatus.OK);
	}
	
	@GetMapping("/consultar-productos/{id}")
	public ResponseEntity<?> consultarProductosDelCliente(@PathVariable Integer id) {
		Collection<ProductoDTO> productosCliente = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			productosCliente = this.iClienteService.consultarProductosPorClienteId(id);
		}catch(DataAccessException e) {
			LOGGER.error("[ERROR] [ClienteRestController.consultarProductosDelCliente(id)] [problemas al consultar los productos del cliente] ".concat(e.getCause().toString()));
			response.put("mensaje", "Error consultado los productos del cliente");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(productosCliente == null) {
			LOGGER.info("[ERROR] [ClienteRestController.consultarProductosDelCliente(id)] [el cliente no existe] ");
			response.put("mensaje", "El cliente ".concat(id.toString()).concat(" no tiene productos."));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		
		return new ResponseEntity<Collection<ProductoDTO>>(productosCliente, HttpStatus.OK);
	}
	
}
