package br.com.ucsal.olimpiadas.view.model.option;

import br.com.ucsal.olimpiadas.application.domain.Resposta;
import br.com.ucsal.olimpiadas.application.service.TentativaAppService;
import br.com.ucsal.olimpiadas.view.model.MenuOpcao;

import java.util.List;

public class ListarTentativasMO extends MenuOpcao {

    private final TentativaAppService tentativaAppService;

    public ListarTentativasMO(TentativaAppService tentativaAppService) {
        super("Listar tentativas (resumo)");
        this.tentativaAppService = tentativaAppService;
    }

    @Override
    public void action() {
        System.out.println("\n--- Tentativas ---");
        var tentativas = tentativaAppService.findAll();

        if (tentativas.isEmpty()) {
            System.out.println("Nenhuma tentativa registrada.");
            return;
        }

        for (var t : tentativas) {
            System.out.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n", t.id(), t.participanteId(),
                    t.provaId(), t.acertos(), t.respostas().size());
        }

    }
}
