package com.edix.cajero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.cajero.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer>{
	
	@Query("select p from Movimiento p where p.cuenta.idCuenta = ?1")
	public List<Movimiento> findByCuenta(int idCuenta);
	
	@Query("select p from Movimiento p where p.cuenta.idCuenta = ?1 order by idMovimiento desc")
	public List<Movimiento> movimientosDesc(int idCuenta);
	
	@Query(value="select * from movimientos where id_Cuenta = ?1 order by id_movimiento desc limit 10", nativeQuery = true)
	public List<Movimiento> findByCuentaDescLimit2(int idCuenta);
	/*
	@Query("select p from Movimiento p where p.cuenta.idCuenta = ?1 order by idMovimiento desc limit 10")
	public List<Movimiento> ultimosMovimientos(int idCuenta);
	*/
}
