package br.com.ucsal.olimpiadas.view.model.option.dto;

import java.util.List;

public class QuestaoRequest {

    private long provaId;
    private String enunciado;
    private char alternativaCorreta;
    private List<String> alternativas;
    private String fenInicial;

    public QuestaoRequest(long provaId, String enunciado, char alternativaCorreta, List<String> alternativas, String fenInicial) {
        this.provaId = provaId;
        this.enunciado = enunciado;
        this.alternativaCorreta = alternativaCorreta;
        this.alternativas = alternativas;
        this.fenInicial = fenInicial;
    }

    public QuestaoRequest(long provaId, String enunciado, char alternativaCorreta, List<String> alternativas) {
        this(provaId, enunciado, alternativaCorreta, alternativas, null);
    }

    public long getProvaId() {
        return provaId;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public char getAlternativaCorreta() {
        return alternativaCorreta;
    }

    public List<String> getAlternativas() {
        return alternativas;
    }

    public String getFenInicial() {
        return fenInicial;
    }
}
