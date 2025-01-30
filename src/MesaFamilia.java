import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Classe que representa uma mesa para famílias em um sistema de reservas.
 * Esta classe herda da classe abstrata Mesa e implementa métodos específicos
 * para calcular a taxa e obter a imagem da mesa para famílias.
 * Além disso, a mesa para famílias inclui uma taxa adicional para brinquedos
 * e uma seleção aleatória de brinquedos disponíveis.
 */
public class MesaFamilia extends Mesa {
    private double taxaBrinquedos; // Taxa adicional para os brinquedos
    private ArrayList<String> brinquedosEscolhidos; // Lista de brinquedos escolhidos aleatoriamente

    // Lista de brinquedos disponíveis para escolha
    private static final ArrayList<String> BRINQUEDOS_DISPONIVEIS = new ArrayList<>(
        Arrays.asList("Carrinho", "Boneca", "Urso de Pelúcia", "Quebra-Cabeça", "Jogos de Tabuleiro", "Cubo Mágico", "Dominó", "Baralho")
    );

    /**
     * Construtor da classe MesaFamilia.
     *
     * @param id O ID da mesa.
     * @param taxaBrinquedos A taxa adicional para os brinquedos.
     * @param localizacao A localização da mesa.
     */
    public MesaFamilia(int id, double taxaBrinquedos, Localizacao localizacao) {
        super(id, taxaBrinquedos, localizacao); // Chama o construtor da classe pai (Mesa)
        this.taxaBrinquedos = taxaBrinquedos;

        // Inicializa a lista de brinquedos escolhidos
        brinquedosEscolhidos = new ArrayList<>();
        Random random = new Random();

        // Seleciona 3 brinquedos aleatórios da lista de brinquedos disponíveis
        for (int i = 0; i < 3; i++) {
            String brinquedoAleatorio = BRINQUEDOS_DISPONIVEIS.get(random.nextInt(BRINQUEDOS_DISPONIVEIS.size()));
            brinquedosEscolhidos.add(brinquedoAleatorio);
        }
    }

    /**
     * Calcula a taxa final da mesa para famílias, incluindo a taxa adicional dos brinquedos.
     *
     * @return A taxa final da mesa.
     */
    @Override
    public double calcularTaxa() {
        return super.getTaxa() + taxaBrinquedos; // Adiciona a taxa dos brinquedos à taxa base
    }

    /**
     * Retorna a imagem associada à mesa para famílias.
     *
     * @return A imagem da mesa para famílias.
     */
    @Override
    public Image getImagem() {
        // Carrega a imagem da mesa para famílias a partir do caminho especificado
        return new ImageIcon("./imagens/mesafamilia.png").getImage();
    }

}