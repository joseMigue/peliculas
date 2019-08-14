package com.jose.core.controllers;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jose.core.constantes.Pagina;
import com.jose.core.model.Pelicula;
import com.jose.core.model.Usuario;
import com.jose.core.service.PeliculaService;
import com.jose.core.service.UsuarioService;

@Controller
@Secured("ROLE_ADMIN")
public class AdministradorController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private PeliculaService peliculaService;
	private static final Log LOG = LogFactory.getLog(AdministradorController.class);
	
	@GetMapping("/administrador")
	public String administrador(Model model) {
		List<Pelicula> listaPeliculas = peliculaService.listaPeliculas();
		model.addAttribute("peliculas", listaPeliculas);	
		model.addAttribute("nombreTabla","Peliculas");
		LOG.info("URL: /administrador  --- METODO: administrador()");
		LOG.info("PARAMETROS ENVIADOS: ");
			LOG.info("peliculas= "+listaPeliculas);
			LOG.info("nombreTabla= "+"Peliculas");
		return Pagina.ADMINISTRADOR;
	}

	@GetMapping("usuarios")
	public String usuarios(Model model) {
		List<Usuario> listaUsuario = usuarioService.listaUsuario();
		model.addAttribute("usuarios", listaUsuario);
		model.addAttribute("nombreTabla","Usuarios");
		LOG.info("URL: /usuarios  ---  METODO: usuarios()");
		LOG.info("PARAMETROS ENVIADOS");
		LOG.info("usuarios: "+listaUsuario);
		LOG.info("nombreTabla: "+"Usuarios");
		return Pagina.ADMINISTRADOR;
	}

	@GetMapping("bloquearUsuario")
	public String bloquearUsuario(@ModelAttribute(name="id")int id) {
		usuarioService.bloquearUsuario(id);
		return "redirect:/usuarios";
	}
	@GetMapping("desbloquearUsuario")
	public String desbloquearUsuario(@ModelAttribute(name="id")int id) {
		usuarioService.desbloquearUsuario(id);
		return "redirect:/usuarios";
	}
	


}
