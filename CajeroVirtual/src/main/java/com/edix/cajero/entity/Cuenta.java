package com.edix.cajero.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cuentas")
public class Cuenta implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cuenta"/*, nullable = false*/)
	private int idCuenta;
	
	@Column(nullable = false)
	private double saldo;
	
	@Column(name="tipo_cuenta"/*, nullable = false*/)
	private String tipo;

	public Cuenta(int id, double saldo, String tipo) {
		super();
		this.idCuenta = id;
		this.saldo = saldo;
		this.tipo = tipo;
	}

	public Cuenta() {
		super();
	}

	public int getId() {
		return idCuenta;
	}

	public void setId(int id) {
		this.idCuenta = id;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	
	@Override
	public String toString() {
		return "Cuenta [id=" + idCuenta + ", saldo=" + saldo + ", tipo=" + tipo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCuenta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		return Objects.equals(idCuenta, other.idCuenta);
	}
	
	
}
