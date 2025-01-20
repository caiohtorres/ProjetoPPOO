import java.util.ArrayList;

public class Reserva {
    private Cliente cliente;
    private Mesa mesa;
    private int tempoReserva;
    private double valorTotal;
    private ArrayList<Pedido> pedidos;

    public Reserva(Cliente cliente, Mesa mesa, int tempoReserva) {
        this.cliente = cliente;
        this.mesa = mesa;
        this.tempoReserva = tempoReserva;
        this.valorTotal = mesa.calcularTaxa();
        pedidos = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public int getTempoReserva() {
        return tempoReserva;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void confirmarReserva() {
        mesa.setDisponibilidade(false);
        System.out.println("Reserva confirmada para o cliente " + cliente.getNome());
    }
}