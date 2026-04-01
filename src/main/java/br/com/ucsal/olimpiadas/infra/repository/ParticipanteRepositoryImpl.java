package br.com.ucsal.olimpiadas.infra.repository;

import br.com.ucsal.olimpiadas.application.domain.Participante;
import br.com.ucsal.olimpiadas.application.ports.ParticipanteRepository;
import br.com.ucsal.olimpiadas.Exceptions.ParticipanteNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParticipanteRepositoryImpl implements ParticipanteRepository {
    static long proximoParticipanteId = 1;

    static final List<Participante> participantes = new ArrayList<>();

    @Override
    public Optional<Participante> findById(long participanteId) {
        var paticipante = participantes.stream().filter(p -> p.getId() == participanteId).findFirst()
                .orElse(null);

        return Optional.ofNullable(paticipante);
    }

    @Override
    public List<Participante> findAll() {
        return List.copyOf(participantes);
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
