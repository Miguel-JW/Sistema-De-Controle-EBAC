import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Quarto[] quartos = new Quarto[5];
    static Reserva[] reservas = new Reserva[50];
    static int totalReservas = 0;

    public static void main(String[] args) {

        // Quartos pré-cadastrados
        quartos[0] = new Quarto(101, "Solteiro",  150.00);
        quartos[1] = new Quarto(102, "Solteiro",  150.00);
        quartos[2] = new Quarto(201, "Casal",     250.00);
        quartos[3] = new Quarto(202, "Casal",     250.00);
        quartos[4] = new Quarto(301, "Suite",     500.00);

        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1: fazerReserva();     break;
                case 2: cancelarReserva();  break;
                case 3: listarReservas();   break;
                case 4: listarQuartos();    break;
                case 5: System.out.println("\nObrigado por usar o sistema. Até logo!"); break;
                default: System.out.println("\nOpção inválida! Tente novamente.");
            }

        } while (opcao != 5);

        scanner.close();
    }

    static void exibirMenu() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("      🏨  Hotel Sistema           ");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("  1 - Fazer Reserva               ");
        System.out.println("  2 - Cancelar Reserva            ");
        System.out.println("  3 - Listar Reservas             ");
        System.out.println("  4 - Ver Quartos Disponíveis     ");
        System.out.println("  5 - Sair                        ");
        System.out.println("╚══════════════════════════════════╝");
        System.out.print("Escolha uma opção: ");
    }

    static void fazerReserva() {
        System.out.println("\n── Nova Reserva ─────────────────");

        // Verifica quartos disponíveis
        boolean temDisponivel = false;
        for (Quarto q : quartos) {
            if (q.isDisponivel()) { temDisponivel = true; break; }
        }
        if (!temDisponivel) {
            System.out.println("⚠ Nenhum quarto disponível no momento!");
            return;
        }

        // Dados do hóspede
        scanner.nextLine();
        System.out.print("Nome do hóspede: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Hospede hospede = new Hospede(nome, cpf, telefone);

        // Exibe quartos disponíveis
        listarQuartos();

        System.out.print("Digite o número do quarto desejado: ");
        int numeroQuarto = scanner.nextInt();

        Quarto quartoEscolhido = null;
        for (Quarto q : quartos) {
            if (q.getNumero() == numeroQuarto && q.isDisponivel()) {
                quartoEscolhido = q;
                break;
            }
        }

        if (quartoEscolhido == null) {
            System.out.println("⚠ Quarto inválido ou indisponível!");
            return;
        }

        // Número de dias
        int dias = 0;
        while (dias <= 0) {
            System.out.print("Quantidade de diárias: ");
            dias = scanner.nextInt();
            if (dias <= 0) System.out.println("⚠ Digite um número válido!");
        }

        reservas[totalReservas] = new Reserva(hospede, quartoEscolhido, dias);
        totalReservas++;

        System.out.println("\n✔ Reserva realizada com sucesso!");
        reservas[totalReservas - 1].exibirReserva();
    }

    static void cancelarReserva() {
        if (totalReservas == 0) {
            System.out.println("\n⚠ Nenhuma reserva cadastrada!");
            return;
        }

        System.out.println("\n── Cancelar Reserva ─────────────");
        listarReservas();

        System.out.print("Digite o número do quarto para cancelar: ");
        int numero = scanner.nextInt();

        boolean encontrou = false;
        for (int i = 0; i < totalReservas; i++) {
            if (reservas[i].getQuarto().getNumero() == numero
                    && reservas[i].getStatus().equals("Ativa")) {
                reservas[i].cancelar();
                System.out.println("\n✔ Reserva do quarto " + numero + " cancelada!");
                encontrou = true;
                break;
            }
        }

        if (!encontrou) System.out.println("⚠ Reserva ativa não encontrada para esse quarto.");
    }

    static void listarReservas() {
        System.out.println("\n── Reservas ─────────────────────");
        if (totalReservas == 0) {
            System.out.println("  Nenhuma reserva cadastrada.");
            return;
        }
        for (int i = 0; i < totalReservas; i++) {
            reservas[i].exibirReserva();
        }
    }

    static void listarQuartos() {
        System.out.println("\n── Quartos Disponíveis ──────────");
        boolean temDisponivel = false;
        for (Quarto q : quartos) {
            if (q.isDisponivel()) {
                q.exibirDados();
                System.out.println();
                temDisponivel = true;
            }
        }
        if (!temDisponivel) System.out.println("  Nenhum quarto disponível.");
    }
}
