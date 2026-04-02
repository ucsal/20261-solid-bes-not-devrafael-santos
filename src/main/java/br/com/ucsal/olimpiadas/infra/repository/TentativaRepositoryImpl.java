package br.com.ucsal.olimpiadas.infra.repository;

import br.com.ucsal.olimpiadas.application.domain.Tentativa;
import br.com.ucsal.olimpiadas.application.ports.TentativaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TentativaRepositoryImpl implements TentativaRepository {
    static long proximaTentativaId = 1;

    static final List<Tentativa> tentativas = new ArrayList<>();

    @Override
    public Optional<Tentativa> findById(long tentativaId) {
        var tentativa = tentativas.stream()
                .filter(t -> t.getId() == tentativaId)
                .findFirst()
                .orElse(null);

        return Optional.ofNullable(tentativa);
    }

    @Override
    public List<Tentativa> findAll() {
        return List.copyOf(tentativas);
    }

    @Override
    public Tentativa create(Tentativa tentativa) {
        tentativa.setId(proximaTentativaId++);
        tentativas.add(tentativa);
        return tentativa;
    }

    @Override
    public boolean isEmpty() {
        return tentativas.isEmpty();
    }
}
