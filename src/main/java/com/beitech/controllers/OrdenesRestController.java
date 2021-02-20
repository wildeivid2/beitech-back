package com.beitech.controllers;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.beitech.dto.OrdenDTO;
import com.beitech.models.Orden;
import com.beitech.services.IClienteService;
import com.beitech.services.IOrdenService;



@RestController
@CrossOrigin({"*"})
@RequestMapping(value = "/api/ordenes")
public class OrdenesRestController {
	
	@Autowired
	IOrdenService iOrdenService;
	
	@Autowired 
	IClienteService iClienteService;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrdenesRestController.class);
	
	
	
	@GetMapping("/ultimas-ordenes-cliente/{id}")
	public ResponseEntity<?> ultimasOrdenesDelCliente(@PathVariable Integer id) {
		Collection<OrdenDTO> ordenes = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			ordenes = this.iOrdenService.listarOrdenesDelUltimoMesPorCliente(id);
		}catch(DataAccessException e) {
			LOGGER.error("[ERROR] [OrdenesRestController.ultimasOrdenesDelCliente(id)] [error al consultar las ultimas ordenes por cliente] ".concat(e.getCause().toString()));
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ParseException e) {
			LOGGER.error("[ERROR] [OrdenesRestController.ultimasOrdenesDelCliente(id)] [problema con la conversion de fecha] ".concat(e.getCause().toString()));
			response.put("mensaje", "Error en el formato de fecha");
			response.put("error", e.getMessage().concat(": ").concat(e.getCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(ordenes == null) {
			LOGGER.info("[INFO] [OrdenesRestController.ultimasOrdenesDelCliente(id)] [el cliente no existe] ");
			response.put("mensaje", "El cliente número ".concat(id.toString()).concat(" no existe en la base de datos..!"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}else if(ordenes.isEmpty()) {
			LOGGER.info("[INFO] [OrdenesRestController.ultimasOrdenesDelCliente(id)] [el cliente no tiene ordenes registradas para este periodo o mes]");
			response.put("mensaje", "El cliente número ".concat(id.toString()).concat(" no tiene ordenes registradas para este período o mes!"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Collection<OrdenDTO>>(ordenes, HttpStatus.OK);
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listarOrdenes(){
		Collection<OrdenDTO> ordenes = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			ordenes = this.iOrdenService.listarOrdenes();
		}catch(DataAccessException e) {
			LOGGER.error("[ERROR] [OrdenesRestController.listarOrdenes()] [error al consultar todas las ordenes] ".concat(e.getCause().toString()));
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(ordenes == null) {
			LOGGER.info("[INFO] [OrdenesRestController.listarOrdenes()] [no hay ordenes] ");
			response.put("mensaje", "No hay ordenes actualmente en la base de datos..!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Collection<OrdenDTO>>(ordenes, HttpStatus.OK);
	}
	
	@GetMapping("/consultar-cliente/{id}")
	public ResponseEntity<?> listarOrdenesPorCliente(@PathVariable Integer id){
		Collection<OrdenDTO> ordenes = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			ordenes = this.iOrdenService.listarOrdenesPorCliente(id);
		}catch(DataAccessException e) {
			LOGGER.error("[ERROR] [OrdenesRestController.listarOrdenesPorCliente(id)] [error al consultar las ordenes por cliente] ".concat(e.getCause().toString()));
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Throwable e) {
			LOGGER.error("[ERROR] [OrdenesRestController.listarOrdenesPorCliente(id)] [error en la consulta de ordenes por cliente] ");
			response.put("mensaje", "Error al consultar las ordenes");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(ordenes == null) {
			LOGGER.info("[INFO] [OrdenesRestController.listarOrdenesPorCliente(id)] [no hay ordenes] ");
			response.put("mensaje", "No hay ordenes actualmente en la base de datos..!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Collection<OrdenDTO>>(ordenes, HttpStatus.OK);
	}
	
	@PostMapping("crear")
	public ResponseEntity<?> crearOrden(@Valid @RequestBody Orden orden, BindingResult result){
		OrdenDTO ordenCreada = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			Collection<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			LOGGER.error("[ERROR] [OrdenesRestController.crearOrden(orden, result)] [hay errores de presentacion] ".concat(result.getNestedPath()));
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			ordenCreada = this.iOrdenService.guardarOrden(orden);
		}catch(DataAccessException e) {
			LOGGER.error("[ERROR] [OrdenesRestController.crearOrden(orden, result)] [error al guardar la nueva orden] ".concat(e.getCause().toString()));
			response.put("mensaje", "Error al guardar la orden");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Throwable e) {
			LOGGER.error("[ERROR] [OrdenesRestController.crearOrden(orden, result)] [error al guardar la nueva orden por validaciones de negocio] ");
			response.put("mensaje", "Error al guardar la orden");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		LOGGER.info("[INFO] [OrdenesRestController.crearOrden(orden, result)] [Orden creada con exito] ");
		response.put("mensaje", "La orden ha sido creada con éxito!");
		response.put("orden", ordenCreada);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
}
