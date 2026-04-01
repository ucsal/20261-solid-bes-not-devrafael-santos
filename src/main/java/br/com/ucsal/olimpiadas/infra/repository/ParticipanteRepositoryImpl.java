package br.com.ucsal.olimpiadas.infra.repository;

import br.com.ucsal.olimpiadas.application.domain.Participante;
import br.com.ucsal.olimpiadas.application.ports.ParticipanteRepository;
import br.com.ucsal.olimpiadas.infra.repository.Exceptions.ParticipanteNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ParticipanteRepositoryImpl implements ParticipanteRepository {
    static long proximoParticipanteId = 1;

    static final List<Participante> participantes = new ArrayList<>();

    @Override
    public Participante findById(long participanteId) {
        return participantes.stream().filter(p -> p.getId() == participanteId).findFirst()
                .orElseThrow(ParticipanteNotFoundException::new);
    }

    @Override
    public List<Participante> findAll() {
        return List.of();
    }

    @Override
    public void create(Participante participante) {
            participante.setId(proximoParticipanteId++);
            participantes.add(participante);
    }

    @Override
    public boolean isEmpty() {
        return participantes.isEmpty();
    }
}
