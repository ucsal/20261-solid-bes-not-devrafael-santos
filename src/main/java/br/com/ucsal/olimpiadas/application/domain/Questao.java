package br.com.ucsal.olimpiadas.application.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Questao {

	private long id;
	private long provaId;

	private String enunciado;
	private List<String> alternativas = new ArrayList<>(5);
	private char alternativaCorreta;

	private String fenInicial;

	public Questao(long provaId, String enunciado, List<String> alternativas, char alternativaCorreta, String fenInicial) {
		this.provaId = provaId;
		this.enunciado = enunciado;
		this.alternativas = alternativas;
		this.alternativaCorreta = alternativaCorreta;
		this.fenInicial = fenInicial;

		validate();
	}

	public Questao(long provaId, String enunciado, List<String> alternativas, char alternativaCorreta) {
		this.provaId = provaId;
		this.enunciado = enunciado;
		this.alternativas = alternativas;
		this.alternativaCorreta = alternativaCorreta;

		validate();
	}

	private void validate() {
		if (alternativas == null || alternativas.size() != 5) {
			throw new IllegalArgumentException("A questão deve possuir exatamente 5 alternativas.");
		}
	}

	public String getFenInicial() {
		return fenInicial;
	}

	public void setFenInicial(String fenInicial) {
		this.fenInicial = fenInicial;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProvaId() {
		return provaId;
	}

	public void setProvaId(long provaId) {
		this.provaId = provaId;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public List<String> getAlternativas() {
		return List.copyOf(alternativas);
	}

	public void setAlternativas(List<String> alternativas) {
		this.alternativas = alternativas;
	}

	public char getAlternativaCorreta() {
		return alternativaCorreta;
	}

	public void setAlternativaCorreta(char alternativaCorreta) {
		this.alternativaCorreta = normalizar(alternativaCorreta);
	}

	public boolean isRespostaCorreta(char marcada) {
		return normalizar(marcada) == alternativaCorreta;
	}

	public static char normalizar(char c) {
		char up = Character.toUpperCase(c);
		if (up < 'A' || up > 'E') {
			throw new IllegalArgumentException("Alternativa deve estar entre A e E.");
		}
		return up;
	}

}
