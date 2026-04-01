package br.com.ucsal.olimpiadas.application.ports;

import br.com.ucsal.olimpiadas.application.domain.Prova;

import java.util.List;
import java.util.Optional;

public interface ProvaRepository {

    public Optional<Prova> findById(long provaId);

    public List<Prova> findAll();

    public Prova create(Prova prova);

    public boolean isEmpty();
}
