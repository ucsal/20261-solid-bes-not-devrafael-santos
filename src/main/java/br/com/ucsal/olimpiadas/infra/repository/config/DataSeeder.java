package br.com.ucsal.olimpiadas.infra.repository.config;

import br.com.ucsal.olimpiadas.application.service.ProvaAppService;
import br.com.ucsal.olimpiadas.application.service.QuestaoAppService;
import br.com.ucsal.olimpiadas.view.model.option.dto.ProvaRequest;
import br.com.ucsal.olimpiadas.view.model.option.dto.QuestaoRequest;

import java.util.List;

public class DataSeeder {

    private final ProvaAppService provaAppService;
    private final QuestaoAppService questaoAppService;

    public DataSeeder(ProvaAppService provaAppService, QuestaoAppService questaoAppService) {
        this.provaAppService = provaAppService;
        this.questaoAppService = questaoAppService;
    }

    public void seed() {

        var provaRequest = new ProvaRequest("Olimpíada 2026 • Nível 1 • Prova A");
        long provaId = provaAppService.create(provaRequest);

        String enunciado = "Questão 1 — Mate em 1.\nÉ a vez das brancas.\nEncontre o lance que dá mate imediatamente.";
        List<String> alternativas = List.of("A) Qh7#", "B) Qf5#", "C) Qc8#", "D) Qh8#", "E) Qe6#");
        String fenInicial = "6k1/5ppp/8/8/8/7Q/6PP/6K1 w - - 0 1";
        char correta = 'C';

        var questaoRequest = new QuestaoRequest(provaId, enunciado, correta, alternativas, fenInicial);
        questaoAppService.create(questaoRequest);
    }
}