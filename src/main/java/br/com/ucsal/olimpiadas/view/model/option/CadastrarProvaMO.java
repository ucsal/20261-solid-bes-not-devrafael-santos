package br.com.ucsal.olimpiadas.view.model.option;

import br.com.ucsal.olimpiadas.application.service.ProvaAppService;
import br.com.ucsal.olimpiadas.view.model.MenuOpcao;
import br.com.ucsal.olimpiadas.view.model.option.dto.ProvaRequest;

import java.util.Scanner;

public class CadastrarProvaMO extends MenuOpcao {
    private final ProvaAppService provaAppService;

    private static final Scanner in = new Scanner(System.in);

    public CadastrarProvaMO(ProvaAppService provaAppService) {
        super("Cadastrar prova");
        this.provaAppService = provaAppService;
    }

    public void action() {
        System.out.print("Título da prova: ");
        var titulo = in.nextLine();

        var provaRequest = new ProvaRequest(titulo);

        long provaId;
        try {
            provaId = provaAppService.create(provaRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Prova criada: " + provaId);
    }
}
