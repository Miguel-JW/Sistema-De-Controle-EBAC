public class Reserva {

    // ── Atributos ──────────────────────────────────────────
    private String nomeHospede;
    private String tipoQuarto;
    private int    numeroDias;
    private double valorDiaria;

    // ── Construtor completo ────────────────────────────────
    public Reserva(String nomeHospede, String tipoQuarto, int numeroDias, double valorDiaria) {
        this.nomeHospede  = nomeHospede;
        this.tipoQuarto   = tipoQuarto;
        this.numeroDias   = numeroDias;
        this.valorDiaria  = valorDiaria;
    }

    // ── Sobrecarga: apenas nome e tipo de quarto ───────────
    public Reserva(String nomeHospede, String tipoQuarto) {
        this(nomeHospede, tipoQuarto, 1, 100.0);
    }

    // ── Getters e Setters ──────────────────────────────────
    public String getNomeHospede()  { return nomeHospede; }
    public String getTipoQuarto()   { return tipoQuarto; }
    public int    getNumeroDias()   { return numeroDias; }
    public double getValorDiaria()  { return valorDiaria; }

    public void setNomeHospede(String nomeHospede)  { this.nomeHospede = nomeHospede; }
    public void setTipoQuarto(String tipoQuarto)    { this.tipoQuarto  = tipoQuarto; }
    public void setNumeroDias(int numeroDias)        { this.numeroDias  = numeroDias; }
    public void setValorDiaria(double valorDiaria)  { this.valorDiaria = valorDiaria; }

    // ── Calcula valor total da hospedagem ──────────────────
    public double calcularValorTotal() {
        return numeroDias * valorDiaria;
    }

    // ── Representação em texto ─────────────────────────────
    @Override
    public String toString() {
        return String.format(
            "┌─────────────────────────────────────┐%n" +
            "  Hóspede:      %-25s%n" +
            "  Quarto:       %-25s%n" +
            "  Dias:         %-25d%n" +
            "  Diária:       R$ %-22.2f%n" +
            "  Total:        R$ %-22.2f%n" +
            "└─────────────────────────────────────┘",
            nomeHospede, tipoQuarto, numeroDias, valorDiaria, calcularValorTotal()
        );
    }
}
