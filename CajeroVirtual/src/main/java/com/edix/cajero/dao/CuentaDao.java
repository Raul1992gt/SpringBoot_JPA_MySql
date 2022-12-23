package com.edix.cajero.dao;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;

import com.edix.cajero.entity.Cuenta;

public interface CuentaDao {

	//Mostrar todos
	public List<Cuenta> findAll();
	
	//Dar paginación desde el servidor (para bbdd grandes, no enviar todo de golpe)
	public Page<Cuenta> findAll(Pageable pageable);
	
	//Buscar por ID
	public Cuenta findById(int id);
	
	//Guardar cuenta
	public Cuenta save(Cuenta cuenta);
	
	//Eliminar cuenta
	public void deleteById(int id);
	
}
