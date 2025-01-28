import javax.swing.ImageIcon;
import java.awt.Image;

public class MesaVIP extends Mesa {
    private double taxaVIP;

    public MesaVIP(int id, double taxaVIP, Localizacao localizacao) {
        super(id, taxaVIP, localizacao);
    }

    @Override
    public double calcularTaxa() {
        return super.getTaxa() + taxaVIP;
    }

    @Override
    public Image getImagem() {
        return new ImageIcon("./imagens/mesavip.png").getImage();
    }

    @Override
    public String getNomeMesa() {
        return super.getNomeMesa();
    }
}
