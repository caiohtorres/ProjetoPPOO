import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class Restaurante {
    private ArrayList<Mesa> listaMesas;
    private String restauranteNome;
    private double caixaTotal;
    private ArrayList<ClienteEspera> filaEspera;
    private ArrayList<Cliente> filaAtendidos;
    private Image imagem;
    private Localizacao localizacao;

    public Restaurante(String restauranteNome, Localizacao localizacao) {
        this.restauranteNome = restauranteNome;
        this.listaMesas = new ArrayList<>();
        this.caixaTotal = 0.0;
        this.filaEspera = new ArrayList<>();
        this.filaAtendidos = new ArrayList<>();
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

    public void adicionarNaFilaEspera(ClienteEspera cliente) {
        filaEspera.add(cliente);
    }

    public Cliente retirarDaFilaEspera(ClienteEspera cliente) {
        Cliente clienteRetirado = cliente.getRepresentante();
        filaAtendidos.remove(clienteRetirado);
        return clienteRetirado;
    }
    

    public void adicionarNaFilaAtendidos(Cliente cliente) {
        filaAtendidos.add(cliente);
    }

    public Cliente retirarDaFilaAtendidos(Cliente cliente) {
        Cliente clienteRetirado = cliente;
        filaAtendidos.remove(cliente);
        return clienteRetirado;
    }

    public ArrayList<ClienteEspera> getFilaEspera() {
        return filaEspera;
    }

    public ArrayList<Cliente> getFilaAtendidos(){
        return filaAtendidos;
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

    public Mesa getMesaAleatoria(){
        ArrayList<Mesa> mesas = getMesas();
        Random random = new Random();
        int indiceAleatorio = random.nextInt(mesas.size());

        return mesas.get(indiceAleatorio);
    }

}
