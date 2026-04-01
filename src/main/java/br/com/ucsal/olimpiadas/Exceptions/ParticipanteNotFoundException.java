package br.com.ucsal.olimpiadas.infra.repository.Exceptions;

public class ParticipanteNotFoundException extends RuntimeException {
    public ParticipanteNotFoundException() {
        super("id inválido");
    }
}
