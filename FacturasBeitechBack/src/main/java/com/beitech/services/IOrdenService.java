package com.beitech.services;

import java.text.ParseException;
import java.util.Collection;

import com.beitech.dto.OrdenDTO;
import com.beitech.models.Orden;

public interface IOrdenService {
	
	public Collection<OrdenDTO> listarOrdenes();
	
	public Collection<OrdenDTO> listarOrdenesPorCliente(Integer id) throws Throwable;
	
	public Collection<OrdenDTO> listarOrdenesDelUltimoMesPorCliente(Integer id) throws ParseException;
	
	public OrdenDTO guardarOrden(Orden orden) throws Throwable;
	
}
