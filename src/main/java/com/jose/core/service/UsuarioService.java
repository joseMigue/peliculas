package com.jose.core.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jose.core.model.Authority;
import com.jose.core.model.Genero;
import com.jose.core.model.Pelicula;
import com.jose.core.model.Usuario;
import com.jose.core.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PeliculaService peliculaService;
	@Autowired
	AuthorityService authorityService;
	BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder(4);
	public Usuario crearUsuario() {
		return new Usuario();
	}
	public List<Usuario> listaUsuario(){
		return usuarioRepository.findAll();
	}
	
	public List<Usuario> listaUsuarioRoleUsuario(){
		Authority authority = authorityService.buscarAuthority("ROLE_USER");
		return usuarioRepository.findByAuthority(authority);
	}
	
	public void bloquearUsuario(int id) {
		Usuario usuario = this.buscarUsuario(id);
		usuario.setEnabled(false);
		usuarioRepository.save(usuario);
	}
	
	public void desbloquearUsuario(int id) {
		Usuario usuario = this.buscarUsuario(id);
		usuario.setEnabled(true);
		usuarioRepository.save(usuario);
	}	
	
	public boolean guardarUsuario(Usuario user) {
		try {
			usuarioRepository.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean guardarUsuarioRegistrado(Usuario usuario) {
		try {
			Authority authority = authorityService.buscarAuthority("ROLE_USER");
			usuario.setAuthority(new HashSet<>());
			usuario.agregarAuthority(authority);
			usuario.setEnabled(true);
			BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder(4);
			usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
			System.out.println(usuario.toString());
			usuarioRepository.save(usuario);
			return true;
		} catch (Exception e) {
			usuario.setPassword("");
			return false;
		}
	}
	public void guardarPeliculaFavorita(int id,String username) {
		try {
			Usuario user = usuarioRepository.findByUsername(username);
			Pelicula peli = peliculaService.buscarPeliculaId(id);
			user.agregarFavorito(peli);
			this.guardarUsuario(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void eliminarPeliculaFavorita(int id,String username) {
		try {
			Usuario user = usuarioRepository.findByUsername(username);
			Pelicula pelicula = peliculaService.buscarPeliculaId(id);
			user.getFavoritos().remove(pelicula);
			this.guardarUsuario(user);
		} catch (Exception e) {

		}
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
		//UserDetails userDetails = new User(user.getUsername(),user.getPassword(),grantList);
		UserDetails userDetails = new User(user.getUsername(),user.getPassword(),user.isEnabled(),true,true,true,grantList);
		return userDetails;
	}
	
}
