package br.com.ucsal.olimpiadas.Exceptions;

public class InvalidFieldException extends RuntimeException {
    public InvalidFieldException(String field) {
        super(field + " inválido");
    }
}
