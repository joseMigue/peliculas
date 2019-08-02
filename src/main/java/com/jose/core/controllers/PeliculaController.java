package com.jose.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jose.core.service.PeliculaService;

@Controller
public class PeliculaController {

	@Autowired
	private PeliculaService peliculaService;
	
	@GetMapping("/peliculas")
	public String listaPeliculas(Model model) {
		model.addAttribute("peliculas", peliculaService.listaPeliculas());
		return "index.html";
	}
	
	@GetMapping("/borrarPelicula")
	public String borrarPelicula(@RequestParam(name = "id")int id) {
		peliculaService.eliminarPelicula(id);
		return "index.html";
	}
}
