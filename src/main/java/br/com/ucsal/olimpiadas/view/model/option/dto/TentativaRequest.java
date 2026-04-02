package br.com.ucsal.olimpiadas.view.model.option.dto;

import java.util.List;

public record TentativaRequest(long participanteId, long provaId, List<RespostaRequest> respostas) { }
