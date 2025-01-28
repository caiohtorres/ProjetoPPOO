public class ClienteEspera {
    private Cliente representante; // Representante do cliente na fila
    private Mesa tipoMesa;       // Tipo de mesa solicitada pelo cliente

    // Construtor
    public ClienteEspera(Cliente representante, Mesa tipoMesa) {
        this.representante = representante;
        this.tipoMesa = tipoMesa;
    }

    // Getter para o tipo da mesa
    public Mesa getTipoMesa() {
        return tipoMesa;
    }

    // Getter para o representante
    public Cliente getRepresentante() {
        return representante;
    }
}
