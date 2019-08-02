package com.jose.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jose.core.model.Pelicula;
import com.jose.core.repository.PeliculaRepository;

@Service
public class PeliculaService {

	@Autowired
	private PeliculaRepository peliculaRepository;
	
	public List<Pelicula> listaPeliculas(){
		return peliculaRepository.findAll();
	}
	
	public void guardarPelicula(Pelicula entity) {
		peliculaRepository.save(entity);
	}
	
	public void eliminarPelicula(Pelicula entity) {
		peliculaRepository.delete(entity);
	}
	
	public void eliminarPelicula(int id) {
		peliculaRepository.deleteById(id);
	}
	
	public Optional<Pelicula> buscarPeliculaId(int id) {
		return peliculaRepository.findById(id);
	}
	
	public Pelicula buscarPeliculaTitulo(String titulo) {
		return peliculaRepository.findByTitulo(titulo);
	}
}
