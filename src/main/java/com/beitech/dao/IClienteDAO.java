package com.beitech.dao;

import org.springframework.data.repository.CrudRepository;

import com.beitech.models.Cliente;

public interface IClienteDAO extends CrudRepository<Cliente, Integer> {
	
	
	
}
