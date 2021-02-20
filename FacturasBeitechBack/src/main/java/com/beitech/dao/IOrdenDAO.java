package com.beitech.dao;

import java.util.Collection;
import java.util.Date;
import org.springframework.data.repository.CrudRepository;
import com.beitech.models.Cliente;
import com.beitech.models.Orden;

public interface IOrdenDAO extends CrudRepository<Orden, Integer> {
	
	public Collection<Orden> findAllByClienteAndFechaCreacionBetween(Cliente cliente, Date fechaInicial, Date fechaFinal);
	
}
