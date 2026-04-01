package br.com.ucsal.olimpiadas.Exceptions;

public class ParticipanteNotFoundException extends RuntimeException {
    public ParticipanteNotFoundException() {
        super("id inválido");
    }
}
