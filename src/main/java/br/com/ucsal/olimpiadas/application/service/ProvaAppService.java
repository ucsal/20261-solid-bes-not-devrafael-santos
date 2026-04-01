package br.com.ucsal.olimpiadas.application.service;

import br.com.ucsal.olimpiadas.Exceptions.InvalidFieldException;
import br.com.ucsal.olimpiadas.Exceptions.IdNotFoundException;
import br.com.ucsal.olimpiadas.application.domain.Prova;
import br.com.ucsal.olimpiadas.application.ports.ProvaRepository;
import br.com.ucsal.olimpiadas.view.model.option.dto.ProvaRequest;
import br.com.ucsal.olimpiadas.view.model.option.dto.ProvaResponse;

import java.util.List;

public class ProvaAppService {
    private final ProvaRepository provaRepository;

    public ProvaAppService(ProvaRepository provaRepository) {
        this.provaRepository = provaRepository;
    }

    public ProvaResponse findById(long provaId) {
        var prova = provaRepository.findById(provaId)
                .orElseThrow(IdNotFoundException::new);

        return new ProvaResponse(prova.getId(), prova.getTitulo());
    }

    public List<ProvaResponse> findAll() {
        return provaRepository.findAll().stream()
                .map(p -> new ProvaResponse(p.getId(), p.getTitulo()))
                .toList();
    }

    public long create(ProvaRequest provaRequest) {

        if (provaRequest.titulo() == null || provaRequest.titulo().isBlank()) {
            throw new InvalidFieldException("titulo");
        }

        var newProva = new Prova(provaRequest.titulo());

        return provaRepository.create(newProva).getId();
    }

    public boolean isEmpty() {
        return provaRepository.isEmpty();
    }
}
