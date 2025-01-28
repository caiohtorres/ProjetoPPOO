import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Reserva {
    private Cliente cliente;
    private Mesa mesa;
    private int tempoReserva;
    private double valorTotal;
    private ArrayList<Pedido> pedidoEscolhido;

    private static final ArrayList<Pedido> pedidosDisponiveis = new ArrayList<>(
        Arrays.asList(new Pedido(1, "Carne", 100), new Pedido(2, "Salada", 200), new Pedido(3, "Rodízio", 1000), new Pedido(4, "Vegan", 50)));

    public Reserva(Cliente cliente, Mesa mesa, int tempoReserva) {
        this.cliente = cliente;
        this.mesa = mesa;
        this.tempoReserva = tempoReserva;
        this.valorTotal = mesa.calcularTaxa();
        pedidoEscolhido = new ArrayList<>();
        Random random = new Random();

        // Seleciona 2 pedidos aleatórios
        for (int i = 0; i < 2; i++) {
            Pedido pedidoAleatorio = pedidosDisponiveis.get(random.nextInt(pedidosDisponiveis.size()));
            pedidoEscolhido.add(pedidoAleatorio);
        }
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

    public void setTempoReserva() {
        this.tempoReserva = tempoReserva - 1;
    }

    public double getValorTotal() {
        for (Pedido pedido : pedidoEscolhido)
            valorTotal += pedido.getValorTotal();
        return valorTotal;
    }

    public void confirmarReserva(Mesa m) {
        m.setDisponibilidade(false);
        System.out.println("Reserva confirmada para o cliente " + cliente.getNome());
    }

    public String getPedidos() {
        StringBuilder conta = new StringBuilder();
        for (Pedido pedido : pedidoEscolhido) {
            conta.append(pedido.getPedido()).append(" | ");
        }
        return conta.toString();
    }
}