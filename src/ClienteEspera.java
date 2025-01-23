public class ClienteEspera {
    private Cliente representante; // Representante do cliente na fila
    private String tipoMesa;       // Tipo de mesa solicitada pelo cliente

    // Construtor
    public ClienteEspera(Cliente representante, String tipoMesa) {
        this.representante = representante;
        this.tipoMesa = tipoMesa;
    }

    // Getter para o tipo da mesa
    public String getTipoMesa() {
        return tipoMesa;
    }

    // Getter para o representante
    public Cliente getRepresentante() {
        return representante;
    }
}
