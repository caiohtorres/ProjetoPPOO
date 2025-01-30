/**
 * Classe que representa um cliente em espera por uma mesa.
 * Cada cliente em espera possui um representante (objeto da classe Cliente)
 * e um tipo de mesa solicitada.
 */
public class ClienteEspera {
    private Cliente representante; // Representante do cliente na fila de espera
    private String tipoMesa;       // Tipo de mesa solicitada pelo cliente

    /**
     * Construtor da classe ClienteEspera.
     *
     * @param representante O cliente que está em espera (representante).
     * @param tipoMesa O tipo de mesa solicitada pelo cliente.
     */
    public ClienteEspera(Cliente representante, String tipoMesa) {
        this.representante = representante;
        this.tipoMesa = tipoMesa;
    }

    /**
     * Retorna o tipo de mesa solicitada pelo cliente.
     *
     * @return O tipo de mesa solicitada.
     */
    public String getTipoMesa() {
        return tipoMesa;
    }

    /**
     * Retorna o representante do cliente em espera.
     *
     * @return O cliente representante.
     */
    public Cliente getRepresentante() {
        return representante;
    }
}