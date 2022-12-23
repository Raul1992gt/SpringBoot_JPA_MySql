package com.edix.cajero.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.cajero.dao.CuentaDao;
import com.edix.cajero.dao.MovimientoDao;
import com.edix.cajero.entity.Cuenta;
import com.edix.cajero.entity.Movimiento;

@Controller
@RequestMapping("/cuentas")
public class CuentaController {

	@Autowired
	private CuentaDao cdao;
	
	@Autowired
	private MovimientoDao mdao;
	
	/**
	 * @param model para pasar la cuenta buscada por id
	 * @return menu.jsp
	 */
	@GetMapping("/porCuenta/{id}")
	public String buscarPorCuenta(Model model) {
		model.addAttribute("cuenta", cdao.findById(2000));
		return "menu";
	}
	
	/**
	 * @param model pasamos el mensaje para pintarlo en el jsp
	 * @return retornamos al menu.jsp
	 */
	@GetMapping("/alta")
	public String procesarAlta(Model model) {
		Cuenta cuenta = new Cuenta(1000,3000,"ahorro");
		if(cdao.save(cuenta) != null) {
			model.addAttribute("mensaje","Alta realizado");				
		} else {
			model.addAttribute("mensaje","Imposible dar alta");
		}
		return "menu";
	}
	
	/**
	 * @return menu.jsp
	 */
	@GetMapping("/menu")
	public String irMenu() {
		return "menu";
	}
	
	/**
	 * @return ingresar.jsp
	 */
	@GetMapping("/ingresar")
	public String irIngresar() {
		return "ingresar";
	}
	
	
	/**
	 * @param attr con RedirectAttribute pasamos los atributos necesarios con POST evitando la eliminación de los parámetros al recargar
	 * @param sesion obtenemos de la sesión el atributo cuenta, para poder intertactuar con el
	 * @param cantidad recuperado del formulario de ingresar para poder sumarlo en la cuenta deseada
	 * @return redireccionamos a ingresar.jsp con el mensaje correspondiente
	 */
	@PostMapping("/ingresar")
	public String procesoIngresar( RedirectAttributes attr,HttpSession sesion, @RequestParam("cantidad") double cantidad) {
	
		Cuenta cuenta = (Cuenta) sesion.getAttribute("cuenta");
		
		
		int contador = 0; //contador para Id's de movimientos
		if(cuenta.getSaldo() != -1) {
			cuenta.setSaldo(cuenta.getSaldo()+cantidad);
			cdao.save(cuenta);
			attr.addFlashAttribute("mensaje","Se ha ingresado correctamente");
			Movimiento movimiento = new Movimiento();
			movimiento.setCantidad(cantidad);
			movimiento.setCuenta(cuenta);
			movimiento.setFechaAlta(new Date());
			movimiento.setOperacion("ingreso");
			movimiento.setId(contador);
			contador++;
			mdao.save(movimiento);
		} else {
			attr.addFlashAttribute("mensaje","No ha sido posible el ingreso");
		}
				
		return "redirect:/cuentas/ingresar";
	}
	
	
	
	/**
	 * @return vamos a extraer.jsp
	 */
	@GetMapping("/extraer")
	public String irExtraer() {
		return "extraer";
	}
	
	/**
	 * @param attr con RedirectAttribute pasamos los atributos necesarios con POST evitando la eliminación de los parámetros al recargar
	 * @param sesion obtenemos de la sesión el atributo cuenta, para poder intertactuar con el
	 * @param cantidad recuperado del formulario de extraer para poder restarlo en la cuenta deseada
	 * @return redireccionamos a extraer.jsp con el mensaje correspondiente
	 */
	@PostMapping("/extraer")
	public String procesoExtraer(RedirectAttributes attr,HttpSession sesion, @RequestParam("cantidad") double cantidad) {
		
		Cuenta cuenta = (Cuenta) sesion.getAttribute("cuenta");
		
		int contador = 0; //contador para Id's de movimientos
		if(cuenta.getSaldo() > cantidad) {
			cuenta.setSaldo(cuenta.getSaldo()-cantidad);
			attr.addFlashAttribute("mensaje","Se ha extraido correctamente");
			cdao.save(cuenta);
			Movimiento movimiento = new Movimiento();
			movimiento.setCantidad(cantidad);
			movimiento.setCuenta(cuenta);
			movimiento.setFechaAlta(new Date());
			movimiento.setOperacion("extracción");
			movimiento.setId(contador);
			contador++;
			mdao.save(movimiento);
		} else {
			attr.addFlashAttribute("mensaje","No ha sido posible la retirada, compruebe su saldo");
		}
				
		return "redirect:/cuentas/extraer";
	}
	
	/**
	 * @param model pasamos el parámetro listaCuentas para poder mostrarlos (Es opcional, yo lo muestro para que veas el movimiento en tiempo real)
	 * @return transeferencia.jsp
	 */
	@GetMapping("/transferencia")
	public String irTransferencia(Model model) {
		model.addAttribute("listaCuentas",cdao.findAll());
		return "transferencia";
	}

	/**
	 * @param attr con RedirectAttribute pasamos los atributos necesarios con POST evitando la eliminación de los parámetros al recargar
	 * @param sesion obtenemos de la sesión el atributo cuenta, para poder intertactuar con el
	 * @param cantidad recuperado del formulario de transferencia para poder restarlo en la cuenta desde donde se hace la transferencia y sumarlo a la que le llega
	 * @param destino cuenta a la que le va a llegar la transferencia, por lo que hay que sumarle la cantidad
	 * @return redireccionamos a transferencia con el mensaje correspondiente
	 */
	@PostMapping("/transferencia")
	public String procesarTransferencia(RedirectAttributes attr,HttpSession sesion, @RequestParam("cantidad") double cantidad, @RequestParam("destino") int destino) {
		
		Cuenta cuenta = (Cuenta) sesion.getAttribute("cuenta");
		
		int contador = 0; //contador para Id's de movimientos
		if(cuenta.getId() == destino) {
			attr.addFlashAttribute("mensaje","Está intentando hacerse una transferencia a tí mismo");
		} if( !mdao.buscarTodos().equals(cuenta) ) {
			attr.addFlashAttribute("mensaje","Cuenta no existente, inténtelo de nuevo");
		}
		if(cdao.findById(destino) != null && cuenta.getId() != destino ) {
			if(cuenta.getSaldo() > cantidad) {
				cuenta.setSaldo(cuenta.getSaldo()-cantidad);
				cdao.save(cuenta);
				attr.addFlashAttribute("mensaje","Se ha realizado la transferencia correctamente");
				Movimiento movimiento = new Movimiento();
				movimiento.setCantidad(cantidad);
				movimiento.setCuenta(cuenta);
				movimiento.setFechaAlta(new Date());
				movimiento.setOperacion("transferencia");
				movimiento.setId(contador);
				contador++;
				mdao.save(movimiento);
				
				cdao.findById(destino).setSaldo(cdao.findById(destino).getSaldo() + cantidad);
				cdao.save(cdao.findById(destino));
				attr.addFlashAttribute("listaCuentas",cdao.findAll());
			}else {
				attr.addFlashAttribute("mensaje","Imposible realizar transferencia, compruebe su saldo");
			}
		}
		return "redirect:/cuentas/transferencia";
	}
	
	
	//Método necesario para formatear fechas
		@InitBinder
		public void initBinder(WebDataBinder webdataBinder) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			webdataBinder
			.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
		}
}
