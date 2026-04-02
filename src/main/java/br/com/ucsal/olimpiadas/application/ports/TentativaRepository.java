package br.com.ucsal.olimpiadas.application.ports;

import br.com.ucsal.olimpiadas.application.domain.Tentativa;

import java.util.List;
import java.util.Optional;

public interface TentativaRepository {

    public Optional<Tentativa> findById(long tentativaId);

    public List<Tentativa> findAll();

    public Tentativa create(Tentativa tentativa);

    public boolean isEmpty();
}
