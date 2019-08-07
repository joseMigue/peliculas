package com.jose.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jose.core.model.Genero;
@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {

	public Genero findByNombre(String nombre);
}
