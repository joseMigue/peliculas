package com.jose.core.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pelicula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPelicula;
	private String titulo;
	private String descripcion;
	private String idiomas;
	
	public Pelicula() {
		
	}
	public Pelicula(int idPelicula, String titulo, String descripcion, String idiomas) {
		super();
		this.idPelicula = idPelicula;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.idiomas = idiomas;
	}
	public int getIdPelicula() {
		return idPelicula;
	}
	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getIdiomas() {
		return idiomas;
	}
	public void setIdiomas(String idiomas) {
		this.idiomas = idiomas;
	}
	@Override
	public String toString() {
		return "Pelicula [idPelicula=" + idPelicula + ", Titulo=" + titulo + ", descripcion=" + descripcion
				+ ", idiomas=" + idiomas + "]";
	}
	
	
	
}
