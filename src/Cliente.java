import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public class Cliente {
    private static int idClientes = 0;
    private int id;
    private String nome;
    private String tipoMesaPreferida;
    private Localizacao localizacaoAtual;
    private Localizacao localizacaoDestino;
    private Image imagem;
    private Reserva reserva;

    public Cliente(String nome, String tipoMesaPreferida, Localizacao localizacao) {
        this.id = idClientes;
        this.nome = nome;
        this.tipoMesaPreferida = tipoMesaPreferida;
        this.localizacaoAtual = localizacao;
        localizacaoDestino = null;
        imagem = carregarImagemAleatoria(tipoMesaPreferida);
        idClientes += 1;
    }

    private Image carregarImagemAleatoria(String tipoMesa) {
        // Lista de nomes de arquivos de imagem
        String[] imagens = { "Imagens/cliente.png", "Imagens/cliente2.png", "Imagens/cliente3.png" };
        int indiceAleatorio = 0;
        if (tipoMesa == "MesaVIP") {
            indiceAleatorio = 0;
        } else if (tipoMesa == "MesaEconomica") {
            indiceAleatorio = 1;
        } else if (tipoMesa == "MesaFamilia") {
            indiceAleatorio = 2;
        }

        // Retorna a imagem correspondente ao índice aleatório
        return new ImageIcon(getClass().getResource(imagens[indiceAleatorio])).getImage();
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

    public String getTipoMesaPreferida() {
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
        System.out.println(nome + " realizou o pedido: " + reserva.toString());
    }

    public void sairRestaurante() {
        System.out.println(nome + " saiu do restaurante.");
    }

    public String getTipoMesa() {
        return tipoMesaPreferida;
    }
}