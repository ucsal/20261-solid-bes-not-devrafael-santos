package br.com.ucsal.olimpiadas.Exceptions;

public class InvalidNameException extends RuntimeException {
    public InvalidNameException() {
        super("nome inválido");
    }
}
