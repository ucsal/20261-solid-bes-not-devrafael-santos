package br.com.ucsal.olimpiadas.infra.repository;

import br.com.ucsal.olimpiadas.application.domain.Prova;
import br.com.ucsal.olimpiadas.application.ports.ProvaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProvaRepositoryImpl implements ProvaRepository {
    static long proximaProvaId = 1;

    static final List<Prova> provas = new ArrayList<>();

    @Override
    public Optional<Prova> findById(long provaId) {
        var paticipante = provas.stream().filter(p -> p.getId() == provaId).findFirst()
                .orElse(null);

        return Optional.ofNullable(paticipante);
    }

    @Override
    public List<Prova> findAll() {
        return List.copyOf(provas);
    }

    @Override
    public Prova create(Prova prova) {
        prova.setId(proximaProvaId++);
        provas.add(prova);
        return prova;
    }

    @Override
    public boolean isEmpty() {
        return provas.isEmpty();
    }
}
