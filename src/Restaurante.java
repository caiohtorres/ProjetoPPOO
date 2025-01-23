import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Restaurante {
    private ArrayList<Mesa> listaMesas;
    private String restauranteNome;
    private double caixaTotal;
    private Queue<ClienteEspera> filaEspera;

    public Restaurante(String restauranteNome) {
        this.restauranteNome = restauranteNome;
        this.listaMesas = new ArrayList<>();
        this.caixaTotal = 0.0;
        this.filaEspera = new LinkedList<>();
    }

    public ArrayList<Mesa> getMesas() {
        return listaMesas;
    }

    public double getCaixaTotal() {
        return caixaTotal;
    }

    public void setCaixaTotal(double valor) {
        this.caixaTotal = valor;
    }

    public void adicionarPagamento(double valor) {
        this.caixaTotal += valor;
    }

    public void adicionarNaFila(ClienteEspera cliente) {
        filaEspera.add(cliente);
    }

    public void retirarDaFila(ClienteEspera cliente) {
        filaEspera.remove(cliente);
    }

    public Queue<ClienteEspera> getFilaEspera() {
        return filaEspera;
    }

    public String getRestauranteNome() {
        return restauranteNome;
    }
}
