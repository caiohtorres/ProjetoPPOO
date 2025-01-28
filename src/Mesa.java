import java.awt.Image;

public abstract class Mesa {
    private int id;
    private boolean disponibilidade;
    protected double taxa;
    private Localizacao localizacaoMesa;

    public Mesa(int id, double taxa, Localizacao localizacao) {
        this.id = id;
        this.taxa = taxa;
        this.disponibilidade = true; // Por padrão, mesa está disponível
        this.localizacaoMesa = localizacao;
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

    public Localizacao getLocalizacaoMesa() {
        return localizacaoMesa;
    }

    public void setLocalizacaoMesa(Localizacao novaLocalizacao) {
        this.localizacaoMesa = novaLocalizacao;
    }

    public String getNomeMesa() {
        return "";
    }

    public double getTaxa(){
        return taxa;
    }

    public abstract double calcularTaxa();

    public abstract Image getImagem();
}
