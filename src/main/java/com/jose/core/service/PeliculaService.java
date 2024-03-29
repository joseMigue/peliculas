package com.jose.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jose.core.model.Genero;
import com.jose.core.model.Pelicula;
import com.jose.core.repository.GeneroRepository;
import com.jose.core.repository.PeliculaRepository;

@Service
public class PeliculaService {

	@Autowired
	private PeliculaRepository peliculaRepository;
	@Autowired
	private GeneroRepository generoRepository;
	
	public Pelicula crearPelicula() {
		return new Pelicula();
	}
	public List<Pelicula> listaPeliculas(){
		return peliculaRepository.findAll();
	}
	
	public boolean guardarPelicula(Pelicula entity) {
		try {
			peliculaRepository.save(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public void eliminarPelicula(Pelicula entity) {
		peliculaRepository.delete(entity);
	}
	
	public void eliminarPelicula(int id) {
		peliculaRepository.deleteById(id);
	}
	
	public Pelicula buscarPeliculaId(int id) {
		return peliculaRepository.getOne(id);
	}
	
	public Pelicula buscarPeliculaTitulo(Pelicula pelicula) {
		return peliculaRepository.findByTitulo(pelicula.getTitulo());
	}
	
	public List<Pelicula> peliculaGenero(String nombre){
		Genero genero = generoRepository.findByNombre(nombre); 
		return peliculaRepository.findByGenero(genero);
	}
}
