import java.awt.Image;
import javax.swing.ImageIcon;

public class Cliente {
    private static int idClientes = 0;
    private int id;
    private String nome;
    private Mesa tipoMesaPreferida;
    private Localizacao localizacaoAtual;
    private Localizacao localizacaoDestino;
    private Image imagem;
    private Reserva reserva;

    public Cliente(String nome, Mesa tipoMesaPreferida, Localizacao localizacao) {
        this.id = idClientes;
        this.nome = nome;
        this.tipoMesaPreferida = tipoMesaPreferida;
        this.localizacaoAtual = localizacao;
        localizacaoDestino = null;
        imagem = new ImageIcon(getClass().getResource("Imagens/cliente.png")).getImage();
        idClientes += 1;
    }

        public void atualizarLocalizacao() {
        if (localizacaoDestino == null || localizacaoAtual.equals(localizacaoDestino)) {
            return;
        }

        // Verificar se o cliente chegou ao destino
        if (localizacaoAtual.equals(localizacaoDestino)) {
            localizacaoDestino = null;
        }
    }


    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }

    public Image getImagem() {
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

    public Mesa getTipoMesaPreferida() {
        return tipoMesaPreferida;
    }

    public void executarAcao() {
        Localizacao destino = getLocalizacaoDestino();
        if (destino != null) {
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