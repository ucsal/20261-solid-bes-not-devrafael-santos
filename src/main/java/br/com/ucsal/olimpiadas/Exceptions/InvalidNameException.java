package br.com.ucsal.olimpiadas.infra.repository.Exceptions;

public class InvalidNameException extends RuntimeException {
    public InvalidNameException() {
        super("nome inválido");
    }
}
