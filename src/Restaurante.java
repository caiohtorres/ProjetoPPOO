import java.util.ArrayList;

/**
 * Classe que representa um restaurante em um sistema de reservas.
 * Esta classe segue o padrão Singleton, garantindo que apenas uma instância do restaurante exista.
 * O restaurante possui uma lista de mesas, um caixa total, filas de espera e atendidos,
 * além de uma imagem associada.
 */
public class Restaurante {
    private ArrayList<Mesa> listaMesas; // Lista de mesas disponíveis no restaurante
    private String restauranteNome; // Nome do restaurante
    private double caixaTotal; // Caixa total do restaurante
    private ArrayList<ClienteEspera> filaEspera; // Fila de clientes em espera
    private ArrayList<Cliente> filaAtendidos; // Fila de clientes atendidos
    private static Restaurante instanciaUnica; // Instância única do restaurante (Singleton)

    /**
     * Construtor privado da classe Restaurante.
     * O construtor é privado para garantir que a classe seja instanciada apenas uma vez (Singleton).
     *
     * @param restauranteNome O nome do restaurante.
     */
    private Restaurante(String restauranteNome) {
        this.restauranteNome = restauranteNome;
        this.listaMesas = new ArrayList<>();
        this.caixaTotal = 0.0;
        this.filaEspera = new ArrayList<>();
        this.filaAtendidos = new ArrayList<>();
    }

    /**
     * Método estático para obter a instância única do restaurante (Singleton).
     *
     * @param restauranteNome O nome do restaurante.
     * @return A instância única do restaurante.
     */
    public static Restaurante getInstance(String restauranteNome) {
        if (instanciaUnica == null) {
            instanciaUnica = new Restaurante(restauranteNome);
        }
        return instanciaUnica;
    }

    /**
     * Retorna a lista de mesas do restaurante.
     *
     * @return A lista de mesas.
     */
    public ArrayList<Mesa> getMesas() {
        return listaMesas;
    }

    /**
     * Retorna o caixa total do restaurante.
     *
     * @return O caixa total.
     */
    public double getCaixaTotal() {
        return caixaTotal;
    }

    /**
     * Adiciona um valor ao caixa total do restaurante.
     *
     * @param valor O valor a ser adicionado ao caixa.
     */
    public void setCaixaTotal(double valor) {
        this.caixaTotal += valor;
    }

    /**
     * Adiciona um cliente à fila de espera.
     *
     * @param cliente O cliente em espera a ser adicionado.
     */
    public void adicionarNaFilaEspera(ClienteEspera cliente) {
        filaEspera.add(cliente);
    }

    /**
     * Remove um cliente da fila de espera e o retorna.
     *
     * @param cliente O cliente em espera a ser removido.
     * @return O cliente removido da fila de espera.
     */
    public Cliente retirarDaFilaEspera(ClienteEspera cliente) {
        Cliente clienteRetirado = cliente.getRepresentante();
        filaAtendidos.remove(clienteRetirado); // Remove o cliente da fila de atendidos
        return clienteRetirado;
    }

    /**
     * Adiciona um cliente à fila de atendidos.
     *
     * @param cliente O cliente a ser adicionado à fila de atendidos.
     */
    public void adicionarNaFilaAtendidos(Cliente cliente) {
        filaAtendidos.add(cliente);
    }

    /**
     * Retorna a fila de clientes em espera.
     *
     * @return A fila de espera.
     */
    public ArrayList<ClienteEspera> getFilaEspera() {
        return filaEspera;
    }

    /**
     * Retorna a fila de clientes atendidos.
     *
     * @return A fila de atendidos.
     */
    public ArrayList<Cliente> getFilaAtendidos() {
        return filaAtendidos;
    }

    /**
     * Retorna o nome do restaurante.
     *
     * @return O nome do restaurante.
     */
    public String getRestauranteNome() {
        return restauranteNome;
    }

    /**
     * Adiciona uma mesa à lista de mesas do restaurante.
     *
     * @param m A mesa a ser adicionada.
     */
    public void adicionarMesa(Mesa m) {
        listaMesas.add(m);
    }
}