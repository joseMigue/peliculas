package com.jose.core.controllers;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.jose.core.model.Genero;
import com.jose.core.model.Pelicula;
import com.jose.core.service.GeneroService;
import com.jose.core.service.PeliculaService;

@Controller
public class PeliculaController {

	private static final String PELICULA_INDEX = "index";
	private static final String PELICULA_FORM = "pelicula_form";
	private static final String PELICULA_SHOW = "pelicula_show";
	
	private static final Log LOG = LogFactory.getLog(PeliculaController.class);
	
	@Autowired
	private PeliculaService peliculaService;
	@Autowired
	private GeneroService generoService;
	
	@GetMapping("/cancel")
	public RedirectView cancel() {
		LOG.info("URL: /cancel -- METHOD: cancel()");
		RedirectView rv = new RedirectView("peliculas");
		return rv;
	}
	@GetMapping("/peliculas")
	public String listaPeliculas(Model model) {
		model.addAttribute("peliculas", peliculaService.listaPeliculas());
		LOG.info("URL : /peliculas  -- METHOD : listaPeliculas()");
		model.addAttribute("generos", generoService.listaGeneros());
		return PELICULA_INDEX;
	}
	
	@GetMapping("/genero")
	public String peliculaComendia(@ModelAttribute(name="genero")String nombre,Model model) {
		LOG.info("URL: /genero  --  METHOD: peliculasGenero()" +nombre);
		model.addAttribute("peliculas", peliculaService.peliculaGenero(nombre));
		model.addAttribute("generos", generoService.listaGeneros());
		return PELICULA_INDEX;
	}
	@GetMapping("/eliminarPelicula")
	public RedirectView eliminarPelicula(@RequestParam(name = "id")int id) {
		LOG.info("URL : /eliminarPelicula  -- METHOD : eliminarPelicula() -- PARAM : ID=  "+id);
		RedirectView rv = new RedirectView("peliculas");
		peliculaService.eliminarPelicula(id);
		return rv;
	}
	
	@GetMapping("/crearPelicula")
	public ModelAndView crearPelicula() {
		LOG.info("URL : /crearPelicula -- METHOD: crearPelicula()");
		ModelAndView mav = new ModelAndView(PELICULA_FORM);
		mav.addObject("pelicula", new Pelicula());
		mav.addObject("generos", generoService.listaGeneros());
		return mav;
	}
	
	@PostMapping("/guardarPelicula")
	public String guardarPelicula(@Valid @ModelAttribute(name = "pelicula")Pelicula pelicula,BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("generos", generoService.listaGeneros());
			return PELICULA_FORM;
		}else {
				if (peliculaService.guardarPelicula(pelicula)) {
					return "redirect:/peliculas";
				}else {
					boolean existe= true;
					model.addAttribute("existe", existe);
					model.addAttribute("generos", generoService.listaGeneros());
					return PELICULA_FORM;
				}
			}		
	}
	
	@GetMapping("/editarPelicula")
	public ModelAndView editarPelicula(@RequestParam(name="id")int id) {
		LOG.info("URL: /editarPelicula -- METHOD: editarPelicula() -- PARAM: id="+id);
		ModelAndView mav = new ModelAndView(PELICULA_FORM);
		Pelicula pelicula = peliculaService.buscarPeliculaId(id);
		LOG.info("Pelicula a editar: "+pelicula.toString());
		mav.addObject("pelicula", pelicula);
		mav.addObject("generos", generoService.listaGeneros());
		return mav;
	}

}
