import java.awt.Image;

import javax.swing.ImageIcon;

public class MesaEconomica extends Mesa {
    private int desconto;

    public MesaEconomica(int id, double taxa, int desconto, Localizacao localizacao) {
        super(id, taxa, localizacao);
        this.desconto = desconto;
    }

    public int getDesconto(){
        return desconto;
    }

    @Override
    public double calcularTaxa() {
        return taxa - desconto;
    }

    @Override
    public Image getImagem() {
        return new ImageIcon("./imagens/mesaeconomica.png").getImage();
    }
}