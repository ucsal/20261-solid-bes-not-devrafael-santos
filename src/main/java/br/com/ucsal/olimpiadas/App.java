package br.com.ucsal.olimpiadas;

import br.com.ucsal.olimpiadas.application.domain.*;
import br.com.ucsal.olimpiadas.application.service.ParticipanteAppService;
import br.com.ucsal.olimpiadas.application.service.ProvaAppService;
import br.com.ucsal.olimpiadas.application.service.QuestaoAppService;
import br.com.ucsal.olimpiadas.application.service.TentativaAppService;
import br.com.ucsal.olimpiadas.infra.repository.ParticipanteRepositoryImpl;
import br.com.ucsal.olimpiadas.infra.repository.ProvaRepositoryImpl;
import br.com.ucsal.olimpiadas.infra.repository.QuestaoRepositoryImpl;
import br.com.ucsal.olimpiadas.infra.repository.TentativaRepositoryImpl;
import br.com.ucsal.olimpiadas.infra.repository.config.DataSeeder;
import br.com.ucsal.olimpiadas.view.model.Menu;
import br.com.ucsal.olimpiadas.view.model.MenuOpcao;
import br.com.ucsal.olimpiadas.view.model.option.*;

import java.util.ArrayList;
import java.util.List;

public class App {

	public static void main(String[] args) {

		var participanteRepository = new ParticipanteRepositoryImpl();
		var provaRepository = new ProvaRepositoryImpl();
		var questaoRepository = new QuestaoRepositoryImpl();
		var tentativaRepository = new TentativaRepositoryImpl();

		var participanteAppService = new ParticipanteAppService(participanteRepository);
		var provaAppService = new ProvaAppService(provaRepository);
		var questaoAppService = new QuestaoAppService(questaoRepository);
		var tentativaAppService = new TentativaAppService(tentativaRepository, questaoRepository);

		var seeder = new DataSeeder(provaAppService, questaoAppService);
		seeder.seed();

		List<MenuOpcao> menuOpcoes = new ArrayList<>();
		menuOpcoes.add(new CadastrarParticipanteMO(participanteAppService));
		menuOpcoes.add(new CadastrarProvaMO(provaAppService));
		menuOpcoes.add(new CadastrarQuestaoMO(questaoAppService, provaAppService));
		menuOpcoes.add(new AplicarProvaMO(participanteAppService, provaAppService, questaoAppService, tentativaAppService));
		menuOpcoes.add(new ListarTentativasMO(tentativaAppService));

		try {
			Menu.createAndLoadMenu("=== OLIMPÍADA DE QUESTÕES (V1) ===", menuOpcoes);
		} catch (RuntimeException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
		}
	}
}