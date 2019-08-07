package com.jose.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jose.core.model.Genero;
import com.jose.core.repository.GeneroRepository;

@Service
public class GeneroService {

	@Autowired
	private GeneroRepository generoRepository;
	
	public List<Genero> listaGeneros(){
		return generoRepository.findAll();
	}
	
	public Genero buscarGenero(int id) {
		return generoRepository.getOne(id);
	}
	public Genero buscarNombre(String nombre) {
		return generoRepository.findByNombre(nombre);
	}
}
