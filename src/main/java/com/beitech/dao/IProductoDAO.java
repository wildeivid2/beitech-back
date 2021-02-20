package com.beitech.dao;

import org.springframework.data.repository.CrudRepository;
import com.beitech.models.Producto;

public interface IProductoDAO extends CrudRepository<Producto, Integer> {
	
	
	
}
