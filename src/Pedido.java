/**
 * Classe que representa um pedido em um sistema de reservas ou restaurante.
 * Cada pedido possui um nome e um valor total associado.
 */
public class Pedido {
    private String nome; // Nome do pedido
    private double valorTotal; // Valor total do pedido

    /**
     * Construtor da classe Pedido.
     *
     * @param nome O nome do pedido.
     * @param valorTotal O valor total do pedido.
     */
    public Pedido(String nome, double valorTotal) {
        this.nome = nome;
        this.valorTotal = valorTotal;
    }

    /**
     * Retorna o nome do pedido.
     *
     * @return O nome do pedido.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o valor total do pedido.
     *
     * @return O valor total do pedido.
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * Retorna uma representação em formato de string do pedido.
     * Inclui o nome e o valor total do pedido.
     *
     * @return Uma string formatada com os detalhes do pedido.
     */
    public String getPedido() {
        return "| Pedido: " + getNome() + " | Valor: " + getValorTotal();
    }
}