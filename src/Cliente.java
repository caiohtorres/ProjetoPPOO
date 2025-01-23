import java.awt.Image;
import javax.swing.ImageIcon;

public class Cliente {
    private int id;
    private String nome;
    private String tipoMesaPreferida;
    private Localizacao localizacaoAtual;
    private Localizacao localizacaoDestino;
    private Image imagem;

    public Cliente(int id, String nome, String tipoMesaPreferida, Localizacao localizacao) {
        this.id = id;
        this.nome = nome;
        this.tipoMesaPreferida = tipoMesaPreferida;
        this.localizacaoAtual = localizacao;
        localizacaoDestino = null;
        imagem = new ImageIcon(getClass().getResource("Imagens/cliente.png")).getImage();
    }

    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }

    public Image getImagem(){
        return imagem;
    }

    public int getId() {
        return id;
    }

    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }

    public String getNome() {
        return nome;
    }

    public String getTipoMesaPreferida() {
        return tipoMesaPreferida;
    }

    public void executarAcao(){
        Localizacao destino = getLocalizacaoDestino();
        if(destino != null){
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(localizacaoDestino);
            setLocalizacaoAtual(proximaLocalizacao);
        }
    } 

    public void realizarPedido() {
        System.out.println(nome + " realizou um pedido.");
    }

    public void sairRestaurante() {
        System.out.println(nome + " saiu do restaurante.");
    }
}