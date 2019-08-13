package com.jose.core.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jose.core.constantes.Pagina;
import com.jose.core.model.Genero;
import com.jose.core.model.Pelicula;
import com.jose.core.model.Usuario;
import com.jose.core.service.GeneroService;
import com.jose.core.service.PeliculaService;
import com.jose.core.service.UsuarioService;

@Controller
public class UsuarioController {
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PeliculaService peliculaService;
	
	@Autowired
	private GeneroService generoService;
	
	private static final Log LOG = LogFactory.getLog(UsuarioController.class);
	
	@GetMapping("/cancel")
	public String cancel() {	
		LOG.info("URL: /cancel --- METODO: cancel()");
		return "redirect:/administrador";
	}
	@GetMapping("/login")
	public String login() {
		LOG.info("URL: /login  --- METODO: login()");
		return Pagina.LOGIN;
	}
	@Secured("ROLE_USER")
	@GetMapping("/usuario")
	public String usuario(Model model, Principal principal) {
		LOG.info("URL: /usuario  ---  METODO: usuario()");
		List peliculas = peliculaService.listaPeliculas();
		List generos = generoService.listaGeneros();
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("generos", generos);
		LOG.info("USUARIO LOGUEADO: "+principal.getName());
		LOG.info("PARAMETROS:"); 
		LOG.info("peliculas: "+peliculas);
		LOG.info("generos: "+generos);
		return Pagina.USUARIO;
	}
	
	@GetMapping("favoritos")
	public String favoritos(Model model,Principal principal) {
		LOG.info("URL: /favoritos  ---  METODO: favoritos()");	
		Set peliculasFavoritas = usuarioService.peliculasFavoritas(principal.getName());
		List generos = generoService.listaGeneros();
		model.addAttribute("peliculas", peliculasFavoritas);
		model.addAttribute("generos", generos);
		LOG.info("PARAMETROS ENVIADOS: ");
		LOG.info("peliculas= "+peliculasFavoritas);
		LOG.info("generos: "+generos);
		return Pagina.USUARIO;
	}
	@Secured("ROLE_ADMIN")
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
	@Secured("ROLE_ADMIN")
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
	@Secured("ROLE_USER")
	@GetMapping("/genero")
	public String peliculaGenero(@ModelAttribute(name="genero")String genero,Model model) {
		List<Pelicula> peliculaGenero = peliculaService.peliculaGenero(genero);
		List<Genero> listaGeneros = generoService.listaGeneros();
		model.addAttribute("peliculas", peliculaGenero);
		model.addAttribute("generos", listaGeneros);
		model.addAttribute("nombreTabla","Peliculas");
		
		LOG.info("URL: /genero   ---   METHOD: peliculasGenero()");
		LOG.info("PARAMETRO RECIBIDO: genero="+genero);
		LOG.info("PARAMETRO ENVIADO: ");
		LOG.info("peliculas= "+peliculaGenero);
		LOG.info("generos= "+listaGeneros);
		LOG.info("nombreTable= "+"Peliculas");
		return Pagina.USUARIO;
	}
	@Secured("ROLE_USER")
	@GetMapping("/agregarFavorito")
	public String agregarFavorito(@ModelAttribute(name="id")int id,Model model) {
		usuarioService.guardarPeliculaFavorita(id);
		return "redirect:/usuario";
	}
}

