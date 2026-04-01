package br.com.ucsal.olimpiadas.Exceptions;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException() {
        super("id inválido");
    }
}
