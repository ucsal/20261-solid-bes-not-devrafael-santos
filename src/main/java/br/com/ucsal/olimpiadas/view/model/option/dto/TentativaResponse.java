package br.com.ucsal.olimpiadas.view.model.option.dto;

import br.com.ucsal.olimpiadas.application.domain.Resposta;

import java.util.List;

public record TentativaResponse(long id, long participanteId, long provaId, List<Resposta> respostas,  int acertos) {}
