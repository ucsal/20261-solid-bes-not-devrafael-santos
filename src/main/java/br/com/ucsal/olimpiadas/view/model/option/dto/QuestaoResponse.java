package br.com.ucsal.olimpiadas.view.model.option.dto;

import java.util.List;

public record QuestaoResponse(long questaoId, long provaId, String enunciado, char alternativaCorreta, List<String> alternativas, String fenInicial) {}
