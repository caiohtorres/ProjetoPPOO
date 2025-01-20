public class MesaEconomica extends Mesa {
    private int desconto;

    public MesaEconomica(int id, double taxa, int desconto) {
        super(id, taxa);
        this.desconto = desconto;
    }

    public int getDesconto(){
        return desconto;
    }

    @Override
    public double calcularTaxa() {
        return taxa - desconto;
    }
}