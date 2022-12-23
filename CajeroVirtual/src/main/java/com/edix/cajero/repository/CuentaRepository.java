package com.edix.cajero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edix.cajero.entity.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer>{

}
