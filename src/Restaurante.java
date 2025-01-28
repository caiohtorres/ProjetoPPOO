import java.awt.Image;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.ImageIcon;

public class Restaurante {
    private ArrayList<Mesa> listaMesas;
    private String restauranteNome;
    private double caixaTotal;
    private Queue<ClienteEspera> filaEspera;
    private Image imagem;
    private Localizacao localizacao;

    public Restaurante(String restauranteNome, Localizacao localizacao) {
        this.restauranteNome = restauranteNome;
        this.listaMesas = new ArrayList<>();
        this.caixaTotal = 0.0;
        this.filaEspera = new LinkedList<>();
        imagem = new ImageIcon(getClass().getResource("Imagens/parede.png")).getImage();
        this.localizacao = localizacao;
    }

    public ArrayList<Mesa> getMesas() {
        return listaMesas;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public double getCaixaTotal() {
        return caixaTotal;
    }

    public void setCaixaTotal(double valor) {
        this.caixaTotal += valor;
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

    public Image getImagem() {
        return imagem;
    }

    public void adicionarMesa(Mesa m) {
        listaMesas.add(m);
    }
}
