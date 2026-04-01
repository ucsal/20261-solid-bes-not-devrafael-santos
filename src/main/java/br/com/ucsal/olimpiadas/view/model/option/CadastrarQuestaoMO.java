package br.com.ucsal.olimpiadas.view.model.option;

import br.com.ucsal.olimpiadas.Exceptions.NoRecordException;
import br.com.ucsal.olimpiadas.application.domain.Questao;
import br.com.ucsal.olimpiadas.application.ports.ProvaRepository;
import br.com.ucsal.olimpiadas.application.ports.QuestaoRepository;
import br.com.ucsal.olimpiadas.application.service.ProvaAppService;
import br.com.ucsal.olimpiadas.application.service.QuestaoAppService;
import br.com.ucsal.olimpiadas.view.model.MenuOpcao;
import br.com.ucsal.olimpiadas.view.model.option.dto.QuestaoRequest;
import br.com.ucsal.olimpiadas.view.model.option.dto.QuestaoResponse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CadastrarQuestaoMO extends MenuOpcao {
    private final QuestaoAppService questaoAppService;
    private final ProvaAppService provaAppService;

    private static final Scanner in = new Scanner(System.in);


    public CadastrarQuestaoMO(QuestaoAppService questaoAppService, ProvaAppService provaAppService) {
        super("Cadastrar questão (A–E) em uma prova");
        this.questaoAppService = questaoAppService;
        this.provaAppService = provaAppService;
    }

    public void action() {
        if (provaAppService.isEmpty()) {
            throw new NoRecordException("prova");
        }

        System.out.println("\nProvas:");

        var provas = provaAppService.findAll();
        for (var p : provas) {
            System.out.printf("  %d) %s%n", p.id(), p.titulo());
        }
        System.out.print("Escolha o id da prova: ");
        long id = Long.parseLong(in.nextLine());

        var provaId = provaAppService.findById(id).id();

        System.out.println("Enunciado:");
        var enunciado = in.nextLine();

        var alternativas = new ArrayList<String>(5);
        for (int i = 0; i < 5; i++) {
            char letra = (char) ('A' + i);
            System.out.print("Alternativa " + letra + ": ");
            alternativas.add(letra + ") " + in.nextLine());
        }

        System.out.print("Alternativa correta (A–E): ");
        char correta;
        try {
            correta = Questao.normalizar(in.nextLine().trim().charAt(0));
        } catch (Exception e) {
            System.out.println("alternativa inválida");
            return;
        }

        var novaQuestao = new QuestaoRequest(provaId, enunciado, correta, alternativas);


        var novaQuestaoId = questaoAppService.create(novaQuestao);

        System.out.println("Questão cadastrada: " + novaQuestaoId + " (na prova " + provaId + ")");
    }
}
