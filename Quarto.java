public class Quarto {
    private int numero;
    private String tipo;
    private double precoPorNoite;
    private boolean disponivel;

    public Quarto(int numero, String tipo, double precoPorNoite) {
        this.numero = numero;
        this.tipo = tipo;
        this.precoPorNoite = precoPorNoite;
        this.disponivel = true;
    }

    public int getNumero() { return numero; }
    public String getTipo() { return tipo; }
    public double getPrecoPorNoite() { return precoPorNoite; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    public void exibirDados() {
        System.out.println("  Quarto:    " + numero + " (" + tipo + ")");
        System.out.println("  Preço:     R$ " + precoPorNoite + "/noite");
        System.out.println("  Status:    " + (disponivel ? "Disponível ✔" : "Ocupado ✘"));
    }
}
