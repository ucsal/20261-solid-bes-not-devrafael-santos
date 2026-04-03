package br.com.ucsal.olimpiadas.view.model.option;

import br.com.ucsal.olimpiadas.application.service.ParticipanteAppService;
import br.com.ucsal.olimpiadas.view.model.MenuOpcao;
import br.com.ucsal.olimpiadas.view.model.option.dto.ParticipanteRequest;

import java.util.Scanner;

public class CadastrarParticipanteMO extends MenuOpcao {

    private final ParticipanteAppService participanteAppService;

    private static final Scanner in = new Scanner(System.in);

    public CadastrarParticipanteMO(ParticipanteAppService participanteAppService) {
        super("Cadastrar participante");
        this.participanteAppService = participanteAppService;
    }

    public void action() {
        System.out.print("Nome: ");
        var nome = in.nextLine();

        System.out.print("Email (opcional): ");
        var email = in.nextLine();

        var newParticipante = new ParticipanteRequest(nome, email);

        long participanteId;
        try {
            participanteId = participanteAppService.create(newParticipante);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Participante cadastrado: " + participanteId);
    }
}
