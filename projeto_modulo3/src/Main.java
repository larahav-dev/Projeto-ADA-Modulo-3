package app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Clientes");
            System.out.println("2 - Produtos");
            System.out.println("3 - Pedidos");
            System.out.println("4 - Cupons");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> System.out.println("Menu Clientes (futuro)");
                case 2 -> System.out.println("Menu Produtos (futuro)");
                case 3 -> System.out.println("Menu Pedidos (futuro)");
                case 4 -> {
                    MenuCupons menuCupons = new MenuCupons();
                    menuCupons.executar();
                }
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }
}
