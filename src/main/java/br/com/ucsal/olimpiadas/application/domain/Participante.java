package br.com.ucsal.olimpiadas.application.domain;

import br.com.ucsal.olimpiadas.Exceptions.InvalidNameException;

public class Participante {
	private long id;
	private String nome;
	private String email;

	public Participante(String nome, String email) {
		this.nome = nome;
		this.email = email;
		validate();
	}

	private void validate() {
		if (this.nome == null || this.nome.isBlank()) {
			throw new InvalidNameException();
		}
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
