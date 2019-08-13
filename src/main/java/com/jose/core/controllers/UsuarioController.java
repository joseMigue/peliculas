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
import com.jose.core.service.GeneroService;
import com.jose.core.service.PeliculaService;
import com.jose.core.service.UsuarioService;

@Controller
@Secured("ROLE_USER")
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

	@GetMapping("/agregarFavorito")
	public String agregarFavorito(@ModelAttribute(name="id")int id,Principal principal,Model model) {
		usuarioService.guardarPeliculaFavorita(id,principal.getName());
		LOG.info("URL: /agregarFavorito   ---   METODO: agregarFavorito()");
		LOG.info("PARAMETRO RECIBIDO: id= "+id);
		return "redirect:/usuario";
	}
	

	@GetMapping("eliminarFavorito")
	public String eliminarFavorito(@ModelAttribute(name="id")int id,Principal principal) {
		usuarioService.eliminarPeliculaFavorita(id, principal.getName());
		LOG.info("URL: /eliminarFavorito   ---   METODO: eliminarFavorito()");
		LOG.info("PARAMETRO RECIBIDO: id= "+id);
		LOG.info("VISTA REDIRIGIDA: /favoritos");
		return "redirect:/favoritos";
	}
}

