package br.com.ucsal.olimpiadas.Exceptions;

public class InvalidQuestionException extends RuntimeException {
    public InvalidQuestionException() {
        super("alternativa inválida");
    }
}
