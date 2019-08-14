package com.jose.core.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String username;
	private String password;
	private boolean enabled;
	private String nombre;
	private String apellido;
	private String telefono;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Authority> authority;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Pelicula> favoritos;
	
	public Usuario() {
		
	}

	public Usuario(String username, String password, boolean enabled, String nombre, String apellido, String telefono,
			Set<Authority> authority, Set<Pelicula> favoritos) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.authority = authority;
		this.favoritos = favoritos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Authority> getAuthority() {
		return authority;
	}

	public void setAuthority(Set<Authority> authority) {
		this.authority = authority;
	}

	public void agregarAuthority(Authority authority) {
		this.authority.add(authority);
	}
	public Set<Pelicula> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(Set<Pelicula> favoritos) {
		this.favoritos = favoritos;
	}
	
	public void agregarFavorito(Pelicula pelicula) {
		this.favoritos.add(pelicula);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", authority="
				+ authority + ", favoritos=" + favoritos + "]";
	}


	
}
