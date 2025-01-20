public class Pedido {
    private int idPedido;
    private String nome;
    private double valorTotal;

    public Pedido(int idPedido, String nome, double valorTotal) {
        this.idPedido = idPedido;
        this.nome = nome;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return idPedido;
    }

    public String getNome() {
        return nome;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
