package com.edix.cajero.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edix.cajero.entity.Cuenta;
import com.edix.cajero.repository.CuentaRepository;

@Service
public class CuentaDaoImpl implements CuentaDao{
	@Autowired
	private CuentaRepository clRep;
	
	@Override
	@Transactional(readOnly = true)
	//Mostrar todos
	public List<Cuenta> findAll() {		
		return clRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	//Dar paginación desde el servidor (para bbdd grandes, no enviar todo de golpe)
	public Page<Cuenta> findAll(Pageable pageable) {		
		return clRep.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	//Buscar por ID
	public Cuenta findById(int id) {		
		return clRep.findById(id).orElse(null);
	}

	@Override
	@Transactional
	//Guardar cuenta, insertar o modificar
	public Cuenta save(Cuenta cuenta) {
		return clRep.save(cuenta);
	}

	@Override
	@Transactional
	//Eliminar cuenta
	public void deleteById(int id) {
		clRep.deleteById(id);		
	}


}
