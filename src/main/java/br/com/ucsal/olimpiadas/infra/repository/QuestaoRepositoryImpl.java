package br.com.ucsal.olimpiadas.infra.repository;

import br.com.ucsal.olimpiadas.application.domain.Questao;
import br.com.ucsal.olimpiadas.application.ports.QuestaoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestaoRepositoryImpl implements QuestaoRepository {

    static long proximaQuestaoId = 1;

    static final List<Questao> questoes = new ArrayList<>();

    @Override
    public Optional<Questao> findById(long questaoId) {
        var questao = questoes.stream().filter(p -> p.getId() == questaoId).findFirst()
                .orElse(null);

        return Optional.ofNullable(questao);
    }

    @Override
    public List<Questao> findAll() {
        return List.copyOf(questoes);
    }

    @Override
    public Questao create(Questao questao) {
        questao.setId(proximaQuestaoId++);
        questoes.add(questao);
        return questao;
    }

    @Override
    public boolean isEmpty() {
        return questoes.isEmpty();
    }
}
