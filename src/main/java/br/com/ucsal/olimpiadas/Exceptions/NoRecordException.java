package br.com.ucsal.olimpiadas.Exceptions;

public class NoRecordException extends RuntimeException {
    public NoRecordException(String item) {
        super("não há " + item + " cadastradas");
    }
}
