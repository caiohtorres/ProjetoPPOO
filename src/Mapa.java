

/**
 * Representa um mapa com todos os itens que participam da simulacao
 * 
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */
public class Mapa {
    private Mesa[][] itens;
    private Cliente[][] clientes;
    private int largura;
    private int altura;
    private Restaurante[][] restaurantes;

    private static final int LARGURA_PADRAO = 20;
    private static final int ALTURA_PADRAO = 20;

    /**
     * Cria mapa para alocar itens da simulacao.
     * 
     * @param largura: largura da área de simulacao.
     * @param altura:  altura da área de simulação.
     */
    public Mapa(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
        itens = new Mesa[altura][largura];
        clientes = new Cliente[altura][largura];
        restaurantes = new Restaurante[altura][largura];
    }

    /**
     * Cria mapa com tamanho padrao.
     */
    public Mapa() {
        this(LARGURA_PADRAO, ALTURA_PADRAO);
    }

    public void adicionarItem(Mesa v) {
        itens[v.getLocalizacaoMesa().getX()][v.getLocalizacaoMesa().getY()] = v;
    }

    public void adicionarCliente(Cliente c) {
        clientes[c.getLocalizacaoAtual().getX()][c.getLocalizacaoAtual().getY()] = c;
    }

    public void adicionarParede(Restaurante r, int linha, int coluna) {
        restaurantes[linha][coluna] = r;
    }

    public void removerItem(Mesa v) {
        itens[v.getLocalizacaoMesa().getX()][v.getLocalizacaoMesa().getY()] = null;
    }

    public void atualizarMapa(Mesa v) {
        removerItem(v);
        adicionarItem(v);
    }

    public Mesa getItem(int x, int y) {
        return itens[x][y];
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public Cliente getCliente(int x, int y) {
        return clientes[x][y];
    }

    public Restaurante getRestaurante(int x, int y) {
        return restaurantes[x][y];
    }



}
