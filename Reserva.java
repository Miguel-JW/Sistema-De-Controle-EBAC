public class Reserva {
    private Hospede hospede;
    private Quarto quarto;
    private int numeroDias;
    private String status;

    public Reserva(Hospede hospede, Quarto quarto, int numeroDias) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.numeroDias = numeroDias;
        this.status = "Ativa";
        quarto.setDisponivel(false);
    }

    public Quarto getQuarto() { return quarto; }
    public String getStatus() { return status; }

    public void cancelar() {
        this.status = "Cancelada";
        quarto.setDisponivel(true);
    }

    public double calcularTotal() {
        return quarto.getPrecoPorNoite() * numeroDias;
    }

    public void exibirReserva() {
        System.out.println("┌─────────────────────────────────┐");
        hospede.exibirDados();
        quarto.exibirDados();
        System.out.println("  Dias:      " + numeroDias);
        System.out.printf("  Total:     R$ %.2f%n", calcularTotal());
        System.out.println("  Status:    " + status);
        System.out.println("└─────────────────────────────────┘");
    }
}
