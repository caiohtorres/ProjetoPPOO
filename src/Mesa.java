import java.awt.Image;


public abstract class Mesa {
    private int id;
    private boolean disponibilidade;
    protected double taxa;
    private Localizacao localizacaoAtual;

    public Mesa(int id, double taxa, Localizacao localizacaoAtual) {
        this.id = id;
        this.taxa = taxa;
        this.disponibilidade = true;
        this.localizacaoAtual = localizacaoAtual;
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

    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public void setLocalizacaoAtual(Localizacao novaLocalizacao) {
        this.localizacaoAtual = novaLocalizacao;
    }

    public abstract double calcularTaxa();

    public abstract Image getImagem();
}
