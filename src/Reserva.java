import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Classe que representa uma reserva em um sistema de reservas.
 * Cada reserva está associada a um cliente, uma mesa, um tempo de reserva,
 * um valor total e uma lista de pedidos escolhidos aleatoriamente.
 */
public class Reserva {
    private Cliente cliente; // Cliente associado à reserva
    private Mesa mesa; // Mesa reservada
    private int tempoReserva; // Tempo de duração da reserva
    private double valorTotal; // Valor total da reserva (incluindo taxa da mesa e pedidos)
    private ArrayList<Pedido> pedidoEscolhido; // Lista de pedidos escolhidos aleatoriamente

    // Lista de pedidos disponíveis para escolha
    private static final ArrayList<Pedido> pedidosDisponiveis = new ArrayList<>(
        Arrays.asList(
            new Pedido("Carne", 100),
            new Pedido("Salada", 200),
            new Pedido("Rodízio", 1000),
            new Pedido("Vegan", 50)
        )
    );

    /**
     * Construtor da classe Reserva.
     *
     * @param cliente O cliente que fez a reserva.
     * @param mesa A mesa reservada.
     * @param tempoReserva O tempo de duração da reserva.
     */
    public Reserva(Cliente cliente, Mesa mesa, int tempoReserva) {
        this.cliente = cliente;
        this.mesa = mesa;
        this.tempoReserva = tempoReserva;
        this.valorTotal = mesa.calcularTaxa(); // Inicializa o valor total com a taxa da mesa
        this.pedidoEscolhido = new ArrayList<>();
        Random random = new Random();

        // Seleciona 2 pedidos aleatórios da lista de pedidos disponíveis
        for (int i = 0; i < 2; i++) {
            Pedido pedidoAleatorio = pedidosDisponiveis.get(random.nextInt(pedidosDisponiveis.size()));
            pedidoEscolhido.add(pedidoAleatorio);
        }
    }

    /**
     * Retorna a mesa associada à reserva.
     *
     * @return A mesa reservada.
     */
    public Mesa getMesa() {
        return mesa;
    }

    /**
     * Retorna o tempo de duração da reserva.
     *
     * @return O tempo de reserva.
     */
    public int getTempoReserva() {
        return tempoReserva;
    }

    /**
     * Reduz o tempo de reserva em 1 unidade.
     * Este método pode ser usado para simular a passagem do tempo.
     */
    public void setTempoReserva() {
        this.tempoReserva = tempoReserva - 1;
    }

    /**
     * Calcula e retorna o valor total da reserva, incluindo a taxa da mesa e os pedidos.
     *
     * @return O valor total da reserva.
     */
    public double getValorTotal() {
        for (Pedido pedido : pedidoEscolhido) {
            valorTotal += pedido.getValorTotal(); // Adiciona o valor de cada pedido ao total
        }
        return valorTotal;
    }

    /**
     * Confirma a reserva, marcando a mesa como indisponível.
     *
     * @param m A mesa que foi reservada.
     */
    public void confirmarReserva(Mesa m) {
        m.setDisponibilidade(false); // Marca a mesa como indisponível
        System.out.println("Reserva confirmada para o cliente " + cliente.getNome());
    }

    /**
     * Retorna uma string com os detalhes dos pedidos associados à reserva.
     *
     * @return Uma string formatada com os pedidos.
     */
    public String getPedidos() {
        StringBuilder conta = new StringBuilder();
        for (Pedido pedido : pedidoEscolhido) {
            conta.append(pedido.getPedido()).append("\n"); // Adiciona cada pedido à string
        }
        return conta.toString();
    }
}