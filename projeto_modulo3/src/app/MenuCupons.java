    package app;
        import entity.Cupom;
        import services;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;


public class MenuCupons {
        private final CupomService cupomService;
    private final BuscarCupomService buscarService;
    private final AtualizarCupomService atualizarService;
    private final ExpirarCupomService expirarService;
    private final MarcarCupomUtilizadoService marcarUtilizadoService;

    public MenuCupons() {
        this.cupomService = new CupomService();
        this.buscarService = new BuscarCupomService(cupomService.getCupons());
        this.atualizarService = new AtualizarCupomService(buscarService);
        this.expirarService = new ExpirarCupomService(buscarService);
        this.marcarUtilizadoService = new MarcarCupomUtilizadoService(buscarService);
    }

    public void executar() {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU CUPONS =====");
            System.out.println("1 - Criar cupom");
            System.out.println("2 - Listar todos cupons");
            System.out.println("3 - Listar cupons disponíveis");
            System.out.println("4 - Atualizar cupom");
            System.out.println("5 - Expirar cupom");
            System.out.println("6 - Marcar cupom como utilizado");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            try {
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Valor do desconto: ");
                        double valor = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("É percentual? (true/false): ");
                        boolean perc = sc.nextBoolean();
                        sc.nextLine();
                        System.out.print("Ano validade: ");
                        int ano = sc.nextInt();
                        System.out.print("Mês validade: ");
                        int mes = sc.nextInt();
                        System.out.print("Dia validade: ");
                        int dia = sc.nextInt();
                        sc.nextLine();

                        Cupom cupom = new Cupom(valor, perc, LocalDate.of(ano, mes, dia));
                        cupomService.criarCupom(cupom);
                        System.out.println("Cupom criado com código: " + cupom.getCodigo());
                    }
                    case 2 -> {
                        List<Cupom> todos = cupomService.listarTodos();
                        todos.forEach(c -> System.out.println(c.getCodigo() + " - desconto: " + c.getValorDesconto()));
                    }
                    case 3 -> {
                        List<Cupom> disponiveis = cupomService.listarCuponsDisponiveis();
                        disponiveis.forEach(c -> System.out.println(c.getCodigo() + " - desconto: " + c.getValorDesconto()));
                    }
                    case 4 -> {
                        System.out.print("Código UUID: ");
                        UUID id = UUID.fromString(sc.nextLine());
                        System.out.print("Novo valor desconto: ");
                        double novoValor = sc.nextDouble();
                        System.out.print("É percentual? (true/false): ");
                        boolean novoPerc = sc.nextBoolean();
                        sc.nextLine();
                        atualizarService.atualizar(id, novoValor, novoPerc);
                        System.out.println("Cupom atualizado!");
                    }
                    case 5 -> {
                        System.out.print("Código UUID: ");
                        UUID id = UUID.fromString(sc.nextLine());
                        expirarService.expirar(id);
                        System.out.println("Cupom expirado!");
                    }
                    case 6 -> {
                        System.out.print("Código UUID: ");
                        UUID id = UUID.fromString(sc.nextLine());
                        marcarUtilizadoService.marcarComoUtilizado(id);
                        System.out.println("Cupom marcado como utilizado!");
                    }
                    case 0 -> System.out.println("Voltando ao menu principal...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 0);
    }    


}