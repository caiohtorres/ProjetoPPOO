public abstract class Mesa {
    protected int id;
    protected boolean disponibilidade;
    protected double taxa;

    public Mesa(int id, double taxa) {
        this.id = id;
        this.taxa = taxa;
        this.disponibilidade = true;
    }

    public int getId() {
        return id;
    }

    public boolean isDisponivel() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public abstract double calcularTaxa();
}
