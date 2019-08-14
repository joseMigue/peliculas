package com.jose.core.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jose.core.constantes.Pagina;
import com.jose.core.model.Usuario;
import com.jose.core.service.UsuarioService;

@Controller
public class LoginController {

	private static final Log LOG = LogFactory.getLog(LoginController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/login")
	public String login() {
		LOG.info("URL: /login  --- METODO: login()");
		return Pagina.LOGIN;
	}
	@GetMapping("/registrar")
	public String register(Model model) {
		LOG.info("URL: /registrar  --- METODO: registrar()");
		model.addAttribute("usuario", new Usuario());
		return "registrar";
	}
	@PostMapping("/guardarUsuario")
	public String guardarUsuario(@ModelAttribute(name="usuario")Usuario usuario) {
		LOG.info("URL: /guardarUsuario  --- METODO: guardarUsuario()");
		LOG.info(usuario.toString());
		boolean ok = usuarioService.guardarUsuarioRegistrado(usuario);
		LOG.info(ok);
		return "login";
	}
}
