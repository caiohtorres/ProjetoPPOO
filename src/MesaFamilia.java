import java.util.ArrayList;
import java.awt.Image;
import javax.swing.ImageIcon;

public class MesaFamilia extends Mesa {
    private double taxaBrinquedos;
    private ArrayList<String> brinquedosDisponiveis;

    public MesaFamilia(int id, double taxaBrinquedos, Localizacao localizacao) {
        super(id, taxaBrinquedos, localizacao);
        brinquedosDisponiveis = new ArrayList<String>();
    }

    @Override
    public double calcularTaxa() {
        return taxa;
    }

    @Override
    public Image getImagem() {
        return new ImageIcon("./imagens/mesafamilia.png").getImage();
    }
}