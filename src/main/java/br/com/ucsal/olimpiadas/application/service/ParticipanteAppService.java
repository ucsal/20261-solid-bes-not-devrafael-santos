package br.com.ucsal.olimpiadas.application.service;

import br.com.ucsal.olimpiadas.Exceptions.IdNotFoundException;
import br.com.ucsal.olimpiadas.Exceptions.InvalidFieldException;
import br.com.ucsal.olimpiadas.application.domain.Participante;
import br.com.ucsal.olimpiadas.view.model.option.dto.ParticipanteRequest;
import br.com.ucsal.olimpiadas.view.model.option.dto.ParticipanteResponse;
import br.com.ucsal.olimpiadas.application.ports.ParticipanteRepository;

import java.util.List;

public class ParticipanteAppService {

    private final ParticipanteRepository participanteRepository;

    public ParticipanteAppService(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    public ParticipanteResponse findById(long participanteId) {
         var participante = participanteRepository.findById(participanteId)
                .orElseThrow(IdNotFoundException::new);

        return new ParticipanteResponse(participante.getId(), participante.getNome(), participante.getEmail());
    }

    public List<ParticipanteResponse> findAll() {
        return participanteRepository.findAll().stream()
                .map(p -> new ParticipanteResponse(p.getId(), p.getNome(), p.getEmail()))
                .toList();
    }

    public long create(ParticipanteRequest participanteRequest) {


        if (participanteRequest.nome() == null || participanteRequest.nome().isBlank()) {
            throw new InvalidFieldException("nome");
        }

        var newParticipante = new Participante(participanteRequest.nome(), participanteRequest.email());

        return participanteRepository.create(newParticipante).getId();
    }

    public boolean isEmpty() {
        return participanteRepository.isEmpty();
    }
}
