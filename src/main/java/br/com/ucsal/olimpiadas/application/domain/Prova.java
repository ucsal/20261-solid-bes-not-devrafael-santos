package br.com.ucsal.olimpiadas.application.domain;

import br.com.ucsal.olimpiadas.Exceptions.InvalidFieldException;

public class Prova {

	private long id;
	private String titulo;

	public Prova(String titulo) {
		this.titulo = titulo;
		validate();
	}

	private void validate() {
		if (titulo == null || titulo.isBlank()) {
			throw new InvalidFieldException("título");
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
