public class MesaVIP extends Mesa {
    private double taxaVIP;

    public MesaVIP(int id, double taxa, double taxaVIP) {
        super(id, taxa);
        this.taxaVIP = taxaVIP;
    }

    @Override
    public double calcularTaxa() {
        return taxa + taxaVIP;
    }
}
