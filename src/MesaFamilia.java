import java.util.ArrayList;

public class MesaFamilia extends Mesa {
    private double taxaBrinquedos;
    private ArrayList<String> brinquedosDisponiveis;

    public MesaFamilia(int id, double taxaBrinquedos) {
        super(id, taxaBrinquedos);
        brinquedosDisponiveis = new ArrayList<String>();
    }

    @Override
    public double calcularTaxa() {
        return taxa;
    }
}