import java.awt.Image;

/**
 * Classe abstrata que representa uma mesa em um sistema de reservas.
 * Cada mesa possui um ID, uma taxa associada, uma localização e uma disponibilidade.
 * Além disso, a classe define métodos abstratos para calcular a taxa e obter a imagem da mesa.
 */
public abstract class Mesa {
    private int id; // ID único da mesa
    private boolean disponibilidade; // Indica se a mesa está disponível ou não
    protected double taxa; // Taxa associada à mesa
    private Localizacao localizacaoMesa; // Localização da mesa

    /**
     * Construtor da classe Mesa.
     *
     * @param id O ID da mesa.
     * @param taxa A taxa associada à mesa.
     * @param localizacao A localização da mesa.
     */
    public Mesa(int id, double taxa, Localizacao localizacao) {
        this.id = id;
        this.taxa = taxa;
        this.disponibilidade = true; // Por padrão, a mesa está disponível
        this.localizacaoMesa = localizacao;
    }

    /**
     * Retorna o ID da mesa.
     *
     * @return O ID da mesa.
     */
    public int getId() {
        return id;
    }

    /**
     * Verifica se a mesa está disponível.
     *
     * @return true se a mesa estiver disponível, false caso contrário.
     */
    public boolean isDisponivel() {
        return disponibilidade;
    }

    /**
     * Define a disponibilidade da mesa.
     *
     * @param disponibilidade true para marcar a mesa como disponível, false para marcá-la como indisponível.
     */
    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    /**
     * Retorna a localização da mesa.
     *
     * @return A localização da mesa.
     */
    public Localizacao getLocalizacaoMesa() {
        return localizacaoMesa;
    }

    /**
     * Retorna a taxa associada à mesa.
     *
     * @return A taxa da mesa.
     */
    public double getTaxa() {
        return taxa;
    }

    /**
     * Método abstrato para calcular a taxa da mesa.
     * Deve ser implementado por subclasses para definir a lógica de cálculo da taxa.
     *
     * @return A taxa calculada.
     */
    public abstract double calcularTaxa();

    /**
     * Método abstrato para obter a imagem da mesa.
     * Deve ser implementado por subclasses para retornar a imagem correspondente ao tipo de mesa.
     *
     * @return A imagem da mesa.
     */
    public abstract Image getImagem();
}