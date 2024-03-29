package com.jose.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jose.core.model.Authority;
import com.jose.core.model.Pelicula;
import com.jose.core.model.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	public Usuario findByUsername(String username);
	
	public List<Pelicula> findByFavoritos(String nombre);
	
	public List<Usuario> findByAuthority(Authority authority);
	
}
