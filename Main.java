import java.util.Scanner;

public class Main {

    // ── Configurações globais ──────────────────────────────
    static final int     CAPACIDADE  = 10;
    static Reserva[]     reservas    = new Reserva[CAPACIDADE];
    static int           total       = 0;
    static Scanner       scanner     = new Scanner(System.in);

    // ══════════════════════════════════════════════════════
    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerInt();

            switch (opcao) {
                case 1: novaReserva();              break;
                case 2: listarReservas();           break;
                case 3: buscarPorNome();            break;
                case 4: ordenarPorDias();           break;
                case 5: mensagemSaida();            break;
                default: System.out.println("\n⚠  Opção inválida! Tente novamente.");
            }
        } while (opcao != 5);

        scanner.close();
    }

    // ── Menu principal ─────────────────────────────────────
    static void exibirMenu() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("        🏨  Sistema de Reservas         ");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("  1 - Nova Reserva                     ");
        System.out.println("  2 - Listar Reservas                  ");
        System.out.println("  3 - Buscar por Nome                  ");
        System.out.println("  4 - Ordenar por Dias (decrescente)   ");
        System.out.println("  5 - Sair                             ");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("  Opção: ");
    }

    // ── Cadastrar nova reserva ─────────────────────────────
    static void novaReserva() {
        System.out.println("\n── Nova Reserva ──────────────────────");

        // Verifica capacidade
        if (total >= CAPACIDADE) {
            System.out.println("⚠  Capacidade máxima (" + CAPACIDADE + " reservas) atingida!");
            return;
        }

        scanner.nextLine();

        // Nome
        System.out.print("Nome do hóspede: ");
        String nome = scanner.nextLine().trim();

        // Tipo de quarto
        System.out.println("Tipos disponíveis: Standard | Luxo | Presidencial");
        System.out.print("Tipo do quarto: ");
        String tipo = scanner.nextLine().trim();

        // Número de dias (mínimo 1)
        int dias = 0;
        while (dias < 1) {
            System.out.print("Número de dias (mínimo 1): ");
            dias = lerInt();
            if (dias < 1) System.out.println("⚠  Número de dias inválido!");
        }

        // Valor da diária (maior que zero)
        double diaria = 0;
        while (diaria <= 0) {
            System.out.print("Valor da diária (R$): ");
            diaria = lerDouble();
            if (diaria <= 0) System.out.println("⚠  Valor da diária inválido!");
        }

        // Cria e armazena a reserva
        reservas[total] = new Reserva(nome, tipo, dias, diaria);
        total++;

        System.out.println("\n✔  Reserva cadastrada com sucesso!");
        System.out.println(reservas[total - 1]);
    }

    // ── Listar todas as reservas ───────────────────────────
    static void listarReservas() {
        System.out.println("\n── Reservas Cadastradas ──────────────");

        if (total == 0) {
            System.out.println("  Nenhuma reserva cadastrada.");
            return;
        }

        for (int i = 0; i < total; i++) {
            System.out.println(reservas[i]);
        }
        System.out.println("  Total de reservas: " + total);
    }

    // ── Buscar reserva por parte do nome ───────────────────
    static void buscarPorNome() {
        System.out.println("\n── Buscar por Nome ───────────────────");

        if (total == 0) {
            System.out.println("  Nenhuma reserva cadastrada.");
            return;
        }

        scanner.nextLine();
        System.out.print("Digite o nome (ou parte dele): ");
        String busca = scanner.nextLine().trim().toLowerCase();

        boolean encontrou = false;
        for (int i = 0; i < total; i++) {
            if (reservas[i].getNomeHospede().toLowerCase().contains(busca)) {
                System.out.println(reservas[i]);
                encontrou = true;
            }
        }

        if (!encontrou) System.out.println("⚠  Nenhuma reserva encontrada para \"" + busca + "\".");
    }

    // ── Ordenar por número de dias (decrescente) ───────────
    static void ordenarPorDias() {
        if (total == 0) {
            System.out.println("\n⚠  Nenhuma reserva para ordenar.");
            return;
        }

        // Bubble Sort decrescente
        for (int i = 0; i < total - 1; i++) {
            for (int j = 0; j < total - i - 1; j++) {
                if (reservas[j].getNumeroDias() < reservas[j + 1].getNumeroDias()) {
                    Reserva temp    = reservas[j];
                    reservas[j]     = reservas[j + 1];
                    reservas[j + 1] = temp;
                }
            }
        }

        System.out.println("\n✔  Reservas ordenadas por dias (maior → menor)!");
        listarReservas();
    }

    // ── Mensagem de saída ──────────────────────────────────
    static void mensagemSaida() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("   Obrigado por usar o Sistema Hotel!  ");
        System.out.println("         Até a próxima! 🏨             ");
        System.out.println("╚══════════════════════════════════════╝");
    }

    // ── Helpers de leitura segura ──────────────────────────
    static int lerInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("⚠  Digite um número válido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    static double lerDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("⚠  Digite um valor válido: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
