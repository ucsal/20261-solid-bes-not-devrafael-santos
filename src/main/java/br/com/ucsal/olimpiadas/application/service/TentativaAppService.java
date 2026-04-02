package br.com.ucsal.olimpiadas.application.service;

import br.com.ucsal.olimpiadas.application.domain.Resposta;
import br.com.ucsal.olimpiadas.application.domain.Tentativa;
import br.com.ucsal.olimpiadas.application.ports.QuestaoRepository;
import br.com.ucsal.olimpiadas.application.ports.TentativaRepository;
import br.com.ucsal.olimpiadas.view.model.option.dto.TentativaRequest;
import br.com.ucsal.olimpiadas.view.model.option.dto.TentativaResponse;

import java.util.List;

public class TentativaAppService {

    private final TentativaRepository tentativaRepository;
    private final QuestaoRepository questaoRepository;

    public TentativaAppService(TentativaRepository tentativaRepository, QuestaoRepository questaoRepository) {
        this.tentativaRepository = tentativaRepository;
        this.questaoRepository = questaoRepository;
    }

    public long newTentativa(TentativaRequest tentativaRequest) {

        var tentativa = new Tentativa(tentativaRequest.participanteId(), tentativaRequest.provaId());

        for (var respReq : tentativaRequest.respostas()) {
            var questao = questaoRepository.findById(respReq.questaoId())
                    .orElseThrow(() -> new IllegalArgumentException("Questão não encontrada"));

            var resposta = new Resposta(
                    questao.getId(),
                    respReq.alternativaMarcada(),
                    questao.isRespostaCorreta(respReq.alternativaMarcada())
            );

            tentativa.getRespostas().add(resposta);
        }

        return tentativaRepository.create(tentativa).getId();
    }

    public List<TentativaResponse> findAll() {
        return tentativaRepository.findAll().stream()
                .map(t -> {
                    int acertos = (int) t.getRespostas().stream().filter(Resposta::isCorreta).count();
                    return new TentativaResponse(t.getId(), t.getParticipanteId(), t.getProvaId(), t.getRespostas(), acertos);
                }).toList();
    }
}