import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MesaFamilia extends Mesa {
    private double taxaBrinquedos;
    private ArrayList<String> brinquedosEscolhidos;

    private static final ArrayList<String> BRINQUEDOS_DISPONIVEIS = new ArrayList<>(
        Arrays.asList("Carrinho", "Boneca", "Urso de Pelúcia", "Quebra-Cabeça", "Jogos de Tabuleiro", "Cubo Mágico", "Dominó", "Baralho")
    );

    public MesaFamilia(int id, double taxaBrinquedos, Localizacao localizacao) {
        super(id, taxaBrinquedos, localizacao);
        this.taxaBrinquedos = taxaBrinquedos;

        brinquedosEscolhidos = new ArrayList<>();
        Random random = new Random();

        // Seleciona 3 brinquedos aleatórios
        for (int i = 0; i < 3; i++) {
            String brinquedoAleatorio = BRINQUEDOS_DISPONIVEIS.get(random.nextInt(BRINQUEDOS_DISPONIVEIS.size()));
            brinquedosEscolhidos.add(brinquedoAleatorio);
        }
    }

    @Override
    public double calcularTaxa() {
        return super.getTaxa() + taxaBrinquedos;
    }

    @Override
    public Image getImagem() {
        return new ImageIcon("./imagens/mesafamilia.png").getImage();
    }

    @Override
    public String getNomeMesa() {
        return super.getNomeMesa();
    }
}
