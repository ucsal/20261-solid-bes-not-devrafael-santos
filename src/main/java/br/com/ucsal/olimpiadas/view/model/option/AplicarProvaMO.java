package br.com.ucsal.olimpiadas.view.model.option;

import br.com.ucsal.olimpiadas.application.domain.Questao;
import br.com.ucsal.olimpiadas.application.service.ParticipanteAppService;
import br.com.ucsal.olimpiadas.application.service.ProvaAppService;
import br.com.ucsal.olimpiadas.application.service.QuestaoAppService;
import br.com.ucsal.olimpiadas.application.service.TentativaAppService;
import br.com.ucsal.olimpiadas.view.model.MenuOpcao;
import br.com.ucsal.olimpiadas.view.model.option.dto.RespostaRequest;
import br.com.ucsal.olimpiadas.view.model.option.dto.TentativaRequest;

import java.util.ArrayList;
import java.util.Scanner;

public class AplicarProvaMO extends MenuOpcao {

    private final ParticipanteAppService participanteAppService;
    private final ProvaAppService provaAppService;
    private final QuestaoAppService questaoAppService;
    private final TentativaAppService tentativaAppService;

    private static final Scanner in = new Scanner(System.in);

    public AplicarProvaMO(ParticipanteAppService participanteAppService, ProvaAppService provaAppService,
                          QuestaoAppService questaoAppService, TentativaAppService tentativaAppService) {
        super("Aplicar prova (selecionar participante + prova)");
        this.participanteAppService = participanteAppService;
        this.provaAppService = provaAppService;
        this.questaoAppService = questaoAppService;
        this.tentativaAppService = tentativaAppService;
    }

    @Override
    public void action() {
        if (participanteAppService.isEmpty()) {
            System.out.println("cadastre participantes primeiro");
            return;
        }

        if (provaAppService.isEmpty()) {
            System.out.println("cadastre provas primeiro");
            return;
        }

        System.out.println("\nParticipantes:");
        participanteAppService.findAll().forEach(p -> System.out.printf("  %d) %s%n", p.id(), p.nome()));
        System.out.print("Escolha o id do participante: ");
        long participanteId = Long.parseLong(in.nextLine());

        System.out.println("\nProvas:");
        provaAppService.findAll().forEach(p -> System.out.printf("  %d) %s%n", p.id(), p.titulo()));
        System.out.print("Escolha o id da prova: ");
        long provaId = Long.parseLong(in.nextLine());

        var questoesDaProva = questaoAppService.findAll().stream()
                .filter(q -> q.provaId() == provaId).toList();

        if (questoesDaProva.isEmpty()) {
            System.out.println("esta prova não possui questões cadastradas");
            return;
        }

        System.out.println("\n--- Início da Prova ---");
        var respostas = new ArrayList<RespostaRequest>();

        for (var q : questoesDaProva) {
            System.out.println("\nQuestão #" + q.questaoId());
            System.out.println(q.enunciado());

            if (q.fenInicial() != null && !q.fenInicial().isBlank()) {
                System.out.println("Posição inicial:");
                imprimirTabuleiroFen(q.fenInicial());
            }

            for (var alt : q.alternativas()) {
                System.out.println(alt);
            }

            System.out.print("Sua resposta (A–E): ");
            char marcada;
            try {
                marcada = Questao.normalizar(in.nextLine().trim().charAt(0));
            } catch (Exception e) {
                System.out.println("resposta inválida (marcando como errada)");
                marcada = 'X';
            }

            respostas.add(new RespostaRequest(q.questaoId(), marcada));
        }

        var tentativaRequest = new TentativaRequest(participanteId, provaId, respostas);
        tentativaAppService.newTentativa(tentativaRequest);

        System.out.println("\n--- Fim da Prova ---");
        System.out.println("Tentativa salva com sucesso!");
    }

    private void imprimirTabuleiroFen(String fen) {
        String parteTabuleiro = fen.split(" ")[0];
        String[] ranks = parteTabuleiro.split("/");

        System.out.println("\n    a b c d e f g h");
        System.out.println("   -----------------");

        for (int r = 0; r < 8; r++) {
            String rank = ranks[r];
            System.out.print((8 - r) + " | ");
            for (char c : rank.toCharArray()) {
                if (Character.isDigit(c)) {
                    int vazios = c - '0';
                    for (int i = 0; i < vazios; i++) {
                        System.out.print(". ");
                    }
                } else {
                    System.out.print(c + " ");
                }
            }
            System.out.println("| " + (8 - r));
        }
        System.out.println("   -----------------");
        System.out.println("    a b c d e f g h\n");
    }
}