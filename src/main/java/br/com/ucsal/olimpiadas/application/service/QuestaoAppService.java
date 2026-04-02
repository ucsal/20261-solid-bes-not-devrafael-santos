package br.com.ucsal.olimpiadas.application.service;

import br.com.ucsal.olimpiadas.Exceptions.IdNotFoundException;
import br.com.ucsal.olimpiadas.Exceptions.InvalidFieldException;
import br.com.ucsal.olimpiadas.Exceptions.NoRecordException;
import br.com.ucsal.olimpiadas.application.domain.Questao;
import br.com.ucsal.olimpiadas.application.ports.ProvaRepository;
import br.com.ucsal.olimpiadas.application.ports.QuestaoRepository;
import br.com.ucsal.olimpiadas.view.model.option.dto.QuestaoRequest;
import br.com.ucsal.olimpiadas.view.model.option.dto.QuestaoResponse;

import java.util.Arrays;
import java.util.List;

public class QuestaoAppService {
    private final QuestaoRepository questaoRepository;

    public QuestaoAppService(QuestaoRepository questaoRepository) {
        this.questaoRepository = questaoRepository;
    }

    public QuestaoResponse findById(long questaoId) {
        var questao = questaoRepository.findById(questaoId)
                .orElseThrow(IdNotFoundException::new);

        return new QuestaoResponse(
                questao.getId(),
                questao.getProvaId(),
                questao.getEnunciado(),
                questao.getAlternativaCorreta(),
                questao.getAlternativas(),
                questao.getFenInicial()
        );
    }

    public List<QuestaoResponse> findAll() {
        return questaoRepository.findAll().stream()
                .map(questao -> new QuestaoResponse(
                        questao.getId(),
                        questao.getProvaId(),
                        questao.getEnunciado(),
                        questao.getAlternativaCorreta(),
                        questao.getAlternativas(),
                        questao.getFenInicial()
                ))
                .toList();
    }

    public long create(QuestaoRequest questaoRequest) {

        var newQuestao = new Questao(questaoRequest.getProvaId(), questaoRequest.getEnunciado(),
                questaoRequest.getAlternativas(), questaoRequest.getAlternativaCorreta());

        return questaoRepository.create(newQuestao).getId();
    }

    public boolean isEmpty() {
        return questaoRepository.isEmpty();
    }
}
