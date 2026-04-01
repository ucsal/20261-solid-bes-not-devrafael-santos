package br.com.ucsal.olimpiadas.application.ports;

import br.com.ucsal.olimpiadas.application.domain.Questao;

import java.util.List;
import java.util.Optional;

public interface QuestaoRepository {

    public Optional<Questao> findById(long questaoId);

    public List<Questao> findAll();

    public Questao create(Questao questao);

    public boolean isEmpty();
}
