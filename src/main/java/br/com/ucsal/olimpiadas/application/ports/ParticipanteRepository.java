package br.com.ucsal.olimpiadas.application.ports;

import br.com.ucsal.olimpiadas.application.domain.Participante;

import java.util.List;
import java.util.Optional;

public interface ParticipanteRepository {

    public Optional<Participante> findById(long participanteId);

    public List<Participante> findAll();

    public void create(Participante participante);

    public boolean isEmpty();
}
