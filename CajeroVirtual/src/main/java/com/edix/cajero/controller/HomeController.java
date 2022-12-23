package com.edix.cajero.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.cajero.dao.CuentaDao;
import com.edix.cajero.entity.Cuenta;

@Controller
public class HomeController {
	
	@Autowired
	private CuentaDao cdao;
	
	@GetMapping("/inicio")
	public String procesarInicio(Model model) {
		model.addAttribute("listaCuentas",cdao.findAll());
		return "login";
	}
	
	
	//Método post con parámetro sesion para guardar el usuario durante un tiempo determinado
	//Usamos RedirectAttributes (en vez de model) para poder pasar atributos como parametros
	//Cliente como parámmetro para poder montarlo
	/**
	 * @param sesion creamos la sesión y le metemos la cuenta buscada por ID
	 * @param attr para poder enviar un mensaje y que no se pierda al redireccionar, en el caso de haber un error al hacer login
	 * @return retornamos, el jsp de menu
	 */
	@PostMapping("/login")
	public String cargarCuenta(HttpSession sesion, RedirectAttributes attr, Cuenta cuenta) {
		if(cdao.findById(cuenta.getId())!= null)
			sesion.setAttribute("cuenta", cdao.findById(cuenta.getId()));
		else {
			attr.addFlashAttribute("mensaje","Cuenta no encontrada");
			return "redirect:/inicio";
		}
		attr.addFlashAttribute("listaCuentas",cdao);
		attr.addFlashAttribute("cuenta",cdao);
		
		return "menu";
	}
	
	//Método necesario para formatear fechas
		@InitBinder
		public void initBinder(WebDataBinder webdataBinder) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			webdataBinder
			.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
		}
}
