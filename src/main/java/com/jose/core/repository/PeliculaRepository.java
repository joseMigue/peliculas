package com.jose.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jose.core.model.Pelicula;
@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {

	public Pelicula findByTitulo(String titulo); 
}
