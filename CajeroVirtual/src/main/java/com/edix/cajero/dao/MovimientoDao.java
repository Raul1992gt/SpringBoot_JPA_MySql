package com.edix.cajero.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.edix.cajero.entity.Movimiento;

public interface MovimientoDao {
	
	/*
	//Mostrat todos los movimientos
	public Iterable<Movimiento> findAll();
	
	//Dar paginación desde el servidor
	public Page<Movimiento> findAll(Pageable pageable);
	
	//Buscar movimientos por ID
	public Optional<Movimiento> findById(int id);
	
	//Guardar movimiento
	public Movimiento save(Movimiento movimiento);
	
	//Eliminar movimiento
	public void deleteById(int id);
	 */
	//Guardar movimiento
		public Movimiento save(Movimiento movimiento);
	Movimiento buscarUno(int idMovimiento);
	List<Movimiento> buscarTodos();
	List<Movimiento> buscarPorCuenta(int idMovimiento);
	int alProducto(Movimiento movimiento);


}
