package com.jose.core.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Genero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idGenero;
	private String nombre;
	
	public Genero() {
		
	}

	public Genero(int idPais, String nombre) {
		super();
		this.idGenero = idPais;
		this.nombre = nombre;
	}


	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idPais) {
		this.idGenero = idPais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Genero [idGenero=" + idGenero + ", nombre=" + nombre + "]";
	}
	
}
