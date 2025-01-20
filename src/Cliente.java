public class Cliente {
    private int id;
    private String nome;
    private String tipoMesaPreferida;

    public Cliente(int id, String nome, String tipoMesaPreferida) {
        this.id = id;
        this.nome = nome;
        this.tipoMesaPreferida = tipoMesaPreferida;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipoMesaPreferida() {
        return tipoMesaPreferida;
    }

    public void realizarPedido() {
        System.out.println(nome + " realizou um pedido.");
    }

    public void sairRestaurante() {
        System.out.println(nome + " saiu do restaurante.");
    }
}