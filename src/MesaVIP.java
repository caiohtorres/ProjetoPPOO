import javax.swing.ImageIcon;
import java.awt.Image;

public class MesaVIP extends Mesa {
    private double taxaVIP;

    public MesaVIP(int id, double taxa, double taxaVIP, Localizacao localizacao) {
        super(id, taxa, localizacao);
        this.taxaVIP = taxaVIP;
    }

    @Override
    public double calcularTaxa() {
        return taxa + taxaVIP;
    }

    @Override
    public Image getImagem() {
        return new ImageIcon("./imagens/mesavip.png").getImage();
    }
}
