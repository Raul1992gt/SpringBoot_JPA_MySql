package com.edix.cajero.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edix.cajero.entity.Movimiento;
import com.edix.cajero.repository.MovimientoRepository;

@Repository
public class MoviminetoDaoImpl implements MovimientoDao{

	@Autowired
	private MovimientoRepository mvRep;
/*	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Movimiento> findAll() {
		return mvRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Movimiento> findAll(Pageable pageable) {
		return mvRep.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Movimiento> findById(int id) {
		return mvRep.findById(id);
	}

	@Override
	@Transactional
	public Movimiento save(Movimiento movimiento) {
		return mvRep.save(movimiento);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		mvRep.deleteById(id);		
	}
 */
	
	@Override
	@Transactional
	public Movimiento save(Movimiento movimiento) {
		return mvRep.save(movimiento);
	}
	
	@Override
	public Movimiento buscarUno(int idMovimiento) {
		// TODO Auto-generated method stub
		return mvRep.findById(idMovimiento).orElse(null);
	}

	@Override
	public List<Movimiento> buscarTodos() {
		// TODO Auto-generated method stub
		return mvRep.findAll();
	}

	@Override
	public List<Movimiento> buscarPorCuenta(int idMovimiento) {
		// TODO Auto-generated method stub
		return mvRep.findByCuenta(idMovimiento);
	}

	@Override
	public int alProducto(Movimiento movimiento) {
		try {
			mvRep.save(movimiento);
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
			
		}
	}


}
