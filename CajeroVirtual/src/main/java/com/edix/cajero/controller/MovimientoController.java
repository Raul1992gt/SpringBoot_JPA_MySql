package com.edix.cajero.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edix.cajero.entity.Cuenta;
import com.edix.cajero.repository.MovimientoRepository;

@Controller
@RequestMapping("/movimientos")
public class MovimientoController {
	
	@Autowired
	private MovimientoRepository mcdao;
	
	/**
	 * @param model con model, pasamos los atributos, cuenta y listaMovimientos al jsp verMovimientos
	 * @param sesion con sesión somos capaces de recuperar los datos de la cuenta itnroducida en sesión al iniciar la aplicación
	 * @param cuenta podemos acceder a la búsqueda de los movimientos de esa cuenta exclusivamente, así, no veríamos los de las demás cuentas
	 * @return verMovimientos.jsp
	 */
	@GetMapping("/verMovimientos")
	public String irMovimientos(Model model, HttpSession sesion,Cuenta cuenta) {
		cuenta = (Cuenta) sesion.getAttribute("cuenta");
		/*
		List<Movimiento> listaMovimientos = new ArrayList<>();
		for(Movimiento mov: mdao.buscarTodos()) {	
			if(mov.getCuenta().getId() == cuenta.getId()) {
				listaMovimientos.add(mov);
			}
			
		}*/
		
		System.out.println(mcdao.findByCuenta(cuenta.getId()));
		model.addAttribute("cuenta",cuenta);
		model.addAttribute("listaMovimientos",mcdao.findByCuentaDescLimit2(cuenta.getId()));
		return "verMovimientos";
	}
	
	@GetMapping("VerTodosMovimientos")
	public String verTodos(Model model, HttpSession sesion, Cuenta cuenta) {
		cuenta = (Cuenta) sesion.getAttribute("cuenta");
		model.addAttribute("cuenta",cuenta);
		model.addAttribute("listaTodosMovimientos",mcdao.movimientosDesc(cuenta.getId()));
		
		return "verTodosMovimientos";
	}
	
}
