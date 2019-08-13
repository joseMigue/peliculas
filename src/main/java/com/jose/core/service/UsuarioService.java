package com.jose.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jose.core.model.Authority;
import com.jose.core.model.Pelicula;
import com.jose.core.model.Usuario;
import com.jose.core.repository.PeliculaRepository;
import com.jose.core.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PeliculaService peliculaService;

	public Usuario crearUsuario() {
		return new Usuario();
	}
	public List<Usuario> listaUsuario(){
		return usuarioRepository.findAll();
	}
	
	public boolean guardarUsuario(Usuario user) {
		try {
			usuarioRepository.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void guardarPeliculaFavorita(int id) {
		Pelicula peli = peliculaService.buscarPeliculaId(id);
		
	}
	public Set<Pelicula> peliculasFavoritas(Usuario usuario){
		
		return usuario.getFavoritos();
	}
	public Set<Pelicula> peliculasFavoritas(String username){
		return this.peliculasFavoritas(usuarioRepository.findByUsername(username));
	}
	public void eliminarUsuario(int id) {
		usuarioRepository.deleteById(id);
	}
	
	public Usuario buscarUsuario(int id) {
		return usuarioRepository.getOne(id);
	}
	public Usuario buscarUsuario(String username) {
		return usuarioRepository.findByUsername(username);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findByUsername(username);
		
		List grantList = new ArrayList();
		for (Authority authority: user.getAuthority()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
			grantList.add(grantedAuthority);
		}
		UserDetails userDetails = new User(user.getUsername(),user.getPassword(),grantList);
		return userDetails;
	}
	
}
