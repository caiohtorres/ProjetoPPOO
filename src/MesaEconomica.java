import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Classe que representa uma mesa econômica em um sistema de reservas.
 * Esta classe herda da classe abstrata Mesa e implementa métodos específicos
 * para calcular a taxa e obter a imagem da mesa econômica.
 */
public class MesaEconomica extends Mesa {
    private int desconto; // Desconto aplicado à taxa da mesa econômica

    /**
     * Construtor da classe MesaEconomica.
     *
     * @param id O ID da mesa.
     * @param taxa A taxa base associada à mesa.
     * @param desconto O desconto aplicado à taxa da mesa.
     * @param localizacao A localização da mesa.
     */
    public MesaEconomica(int id, double taxa, int desconto, Localizacao localizacao) {
        super(id, taxa, localizacao); // Chama o construtor da classe pai (Mesa)
        this.desconto = desconto;
    }

    /**
     * Retorna o desconto aplicado à taxa da mesa.
     *
     * @return O valor do desconto.
     */
    public int getDesconto() {
        return desconto;
    }

    /**
     * Calcula a taxa final da mesa econômica, aplicando o desconto.
     *
     * @return A taxa final após o desconto.
     */
    @Override
    public double calcularTaxa() {
        return super.getTaxa() - desconto; // Subtrai o desconto da taxa base
    }

    /**
     * Retorna a imagem associada à mesa econômica.
     *
     * @return A imagem da mesa econômica.
     */
    @Override
    public Image getImagem() {
        // Carrega a imagem da mesa econômica a partir do caminho especificado
        return new ImageIcon("./imagens/mesaeconomica.png").getImage();
    }
}