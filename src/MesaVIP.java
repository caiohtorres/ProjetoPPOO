import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * Classe que representa uma mesa VIP em um sistema de reservas.
 * Esta classe herda da classe abstrata Mesa e implementa métodos específicos
 * para calcular a taxa e obter a imagem da mesa VIP.
 */
public class MesaVIP extends Mesa {
    private double taxaVIP; // Taxa adicional para a mesa VIP

    /**
     * Construtor da classe MesaVIP.
     *
     * @param id O ID da mesa.
     * @param taxaVIP A taxa adicional para a mesa VIP.
     * @param localizacao A localização da mesa.
     */
    public MesaVIP(int id, double taxaVIP, Localizacao localizacao) {
        super(id, taxaVIP, localizacao); // Chama o construtor da classe pai (Mesa)
        this.taxaVIP = taxaVIP;
    }

    /**
     * Calcula a taxa final da mesa VIP, incluindo a taxa adicional.
     *
     * @return A taxa final da mesa VIP.
     */
    @Override
    public double calcularTaxa() {
        return super.getTaxa() + taxaVIP; // Adiciona a taxa VIP à taxa base
    }

    /**
     * Retorna a imagem associada à mesa VIP.
     *
     * @return A imagem da mesa VIP.
     */
    @Override
    public Image getImagem() {
        // Carrega a imagem da mesa VIP a partir do caminho especificado
        return new ImageIcon("./imagens/mesavip.png").getImage();
    }
}