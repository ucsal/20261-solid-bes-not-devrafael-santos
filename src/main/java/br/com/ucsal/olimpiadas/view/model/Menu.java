package br.com.ucsal.olimpiadas.view.model;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private String titulo;
    private List<MenuOpcao> menuOpcoes;

    private static final Scanner in = new Scanner(System.in);


    private Menu(String titulo, List<MenuOpcao> menuOpcoes) {
        this.titulo = titulo;
        this.menuOpcoes = menuOpcoes;

        int opcao = -1;
        do {
            draw();

            String input = in.nextLine().trim();

            try{
                opcao = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("opção inválida");
                continue;
            }

            if(opcao == 0) {
                System.out.println("tchau");
                return;
            }

            if(opcao < 0 || opcao > menuOpcoes.size()) {
                System.out.println("opção inválida");
                continue;
            }

            menuOpcoes.get(opcao - 1).action();

        } while (true);

    }

    public static void createAndLoadMenu(String titulo, List<MenuOpcao> menuOpcoes) {
        new Menu(titulo, menuOpcoes);
    }

    private void draw() {
        System.out.println("\n " + this.titulo);
        for (int i = 0; i < menuOpcoes.size(); i++) {
            System.out.println((i + 1) + ") " + menuOpcoes.get(i).getTitulo());
        }
        System.out.println("0) Sair");
        System.out.print("> ");
    }
}
