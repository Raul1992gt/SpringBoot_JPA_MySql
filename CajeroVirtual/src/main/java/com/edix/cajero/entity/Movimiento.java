package com.edix.cajero.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="movimientos")
public class Movimiento implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_movimiento")
	private int idMovimiento;
	
	//Declaramos ManyToOne, para representar que muchos movimientos en una cuenta
	@ManyToOne
	@JoinColumn(name="id_cuenta",nullable = false)
	private Cuenta cuenta;
	
	@Column(name="fecha")
	private Date fechaAlta;
	
	@Column(name="cantidad")
	private double cantidad;
	
	@Column(name="operacion")
	private String operacion;

	public Movimiento(int id, Cuenta cuenta, Date fechaAlta, double cantidad, String operacion) {
		super();
		this.idMovimiento = id;
		this.cuenta = cuenta;
		this.fechaAlta = fechaAlta;
		this.cantidad = cantidad;
		this.operacion = operacion;
	}

	public Movimiento() {
		super();
	}

	public int getId() {
		return idMovimiento;
	}

	public void setId(int id) {
		this.idMovimiento = id;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	@Override
	public String toString() {
		return "Movimientos [id=" + idMovimiento + ", cuenta=" + cuenta + ", fechaAlta=" + fechaAlta + ", cantidad=" + cantidad
				+ ", operacion=" + operacion + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMovimiento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimiento other = (Movimiento) obj;
		return Objects.equals(idMovimiento, other.idMovimiento);
	}
	
	
}
