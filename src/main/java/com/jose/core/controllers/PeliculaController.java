package com.jose.core.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.jose.core.constantes.Pagina;
import com.jose.core.model.Genero;
import com.jose.core.model.Pelicula;
import com.jose.core.service.GeneroService;
import com.jose.core.service.PeliculaService;

@Controller
@Secured("ROLE_ADMIN")
public class PeliculaController {
	
	private static final Log LOG = LogFactory.getLog(PeliculaController.class);
	
	@Autowired
	private PeliculaService peliculaService;
	@Autowired
	private GeneroService generoService;
	


	@GetMapping("/eliminarPelicula")
	public RedirectView eliminarPelicula(@RequestParam(name = "id")int id) {	
		LOG.info("URL : /eliminarPelicula   ---  METHOD : eliminarPelicula()");
		LOG.info("PARAMETRO RECIBIDO: id="+id);
		LOG.info("ID de la pelicula a eliminar : "+id);
		RedirectView rv = new RedirectView(Pagina.ADMINISTRADOR);
		peliculaService.eliminarPelicula(id);

		return rv;
	}
	
	@GetMapping("/crearPelicula")
	public ModelAndView crearPelicula() {
		LOG.info("URL : /crearPelicula -- METHOD: crearPelicula()");
		ModelAndView mav = new ModelAndView(Pagina.PELICULA_FORM);
		mav.addObject("pelicula", new Pelicula());
		mav.addObject("generos", generoService.listaGeneros());
		return mav;
	}
	
	@PostMapping("/guardarPelicula")
	public String guardarPelicula(@Valid @ModelAttribute(name = "pelicula")Pelicula pelicula,BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("generos", generoService.listaGeneros());
			return Pagina.PELICULA_FORM;
		}else {
				if (peliculaService.guardarPelicula(pelicula)) {
					return "redirect:/administrador";
				}else {
					boolean existe= true;
					model.addAttribute("existe", existe);
					model.addAttribute("generos", generoService.listaGeneros());
					return Pagina.PELICULA_FORM;
				}
			}		
	}
	
	@GetMapping("/editarPelicula")
	public ModelAndView editarPelicula(@RequestParam(name="id")int id) {
		LOG.info("URL: /editarPelicula -- METHOD: editarPelicula() -- PARAM: id="+id);
		ModelAndView mav = new ModelAndView(Pagina.PELICULA_FORM);
		Pelicula pelicula = peliculaService.buscarPeliculaId(id);
		LOG.info("Pelicula a editar: "+pelicula.toString());
		mav.addObject("pelicula", pelicula);
		mav.addObject("generos", generoService.listaGeneros());
		return mav;
	}

}
