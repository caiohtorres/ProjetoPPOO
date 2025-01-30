import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Classe que representa um cliente em um sistema de reservas.
 * Cada cliente possui um ID único, nome, localização atual, localização de destino,
 * uma imagem associada e uma reserva.
 */
public class Cliente {
    private static int idClientes = 0; // Contador estático para gerar IDs únicos
    private int id; // ID único do cliente
    private String nome; // Nome do cliente
    private Localizacao localizacaoAtual; // Localização atual do cliente
    private Localizacao localizacaoDestino; // Localização de destino do cliente
    private Image imagem; // Imagem associada ao cliente
    private Reserva reserva; // Reserva associada ao cliente

    /**
     * Construtor da classe Cliente.
     *
     * @param nome Nome do cliente.
     * @param tipoMesaPreferida Tipo de mesa preferida pelo cliente (usado para carregar a imagem).
     * @param localizacao Localização inicial do cliente.
     */
    public Cliente(String nome, String tipoMesaPreferida, Localizacao localizacao) {
        this.id = idClientes; // Atribui o ID atual ao cliente
        this.nome = nome;
        this.localizacaoAtual = localizacao;
        this.localizacaoDestino = null; // Inicialmente, o cliente não tem destino
        this.imagem = carregarImagemAleatoria(tipoMesaPreferida); // Carrega a imagem com base no tipo de mesa
        idClientes += 1; // Incrementa o contador de IDs
    }

    /**
     * Carrega uma imagem aleatória com base no tipo de mesa preferida.
     *
     * @param tipoMesa Tipo de mesa preferida pelo cliente.
     * @return A imagem correspondente ao tipo de mesa.
     */
    private Image carregarImagemAleatoria(String tipoMesa) {
        // Lista de nomes de arquivos de imagem
        String[] imagens = { "Imagens/cliente.png", "Imagens/cliente2.png", "Imagens/cliente3.png" };
        int indiceAleatorio = 0;

        // Define o índice da imagem com base no tipo de mesa
        if (tipoMesa.equals("MesaVIP")) {
            indiceAleatorio = 0;
        } else if (tipoMesa.equals("MesaEconomica")) {
            indiceAleatorio = 1;
        } else if (tipoMesa.equals("MesaFamilia")) {
            indiceAleatorio = 2;
        }

        // Retorna a imagem correspondente ao índice
        return new ImageIcon(getClass().getResource(imagens[indiceAleatorio])).getImage();
    }

    /**
     * Atualiza a localização atual do cliente.
     * Se o cliente já estiver no destino, a localização de destino é definida como null.
     */
    public void atualizarLocalizacao() {
        if (localizacaoDestino == null || localizacaoAtual.equals(localizacaoDestino)) {
            return; // Não faz nada se não houver destino ou se o cliente já estiver no destino
        }

        // Verifica se o cliente chegou ao destino
        if (localizacaoAtual.equals(localizacaoDestino)) {
            localizacaoDestino = null; // Remove o destino ao chegar
        }
    }

    /**
     * Retorna a localização atual do cliente.
     *
     * @return A localização atual do cliente.
     */
    public Localizacao getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    /**
     * Define a reserva associada ao cliente.
     *
     * @param reserva A reserva a ser associada ao cliente.
     */
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    /**
     * Retorna a reserva associada ao cliente.
     *
     * @return A reserva do cliente.
     */
    public Reserva getReserva() {
        return reserva;
    }

    /**
     * Retorna a localização de destino do cliente.
     *
     * @return A localização de destino do cliente.
     */
    public Localizacao getLocalizacaoDestino() {
        return localizacaoDestino;
    }

    /**
     * Retorna a imagem associada ao cliente.
     *
     * @return A imagem do cliente.
     */
    public Image getImagem() {
        return imagem;
    }

    /**
     * Retorna o ID do cliente.
     *
     * @return O ID do cliente.
     */
    public int getId() {
        return id;
    }

    /**
     * Define a localização atual do cliente.
     *
     * @param localizacaoAtual A nova localização atual do cliente.
     */
    public void setLocalizacaoAtual(Localizacao localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    /**
     * Define a localização de destino do cliente.
     *
     * @param localizacaoDestino A nova localização de destino do cliente.
     */
    public void setLocalizacaoDestino(Localizacao localizacaoDestino) {
        this.localizacaoDestino = localizacaoDestino;
    }

    /**
     * Retorna o nome do cliente.
     *
     * @return O nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Executa a ação do cliente, movendo-o em direção ao destino.
     * Se o cliente tiver um destino definido, sua localização atual é atualizada.
     */
    public void executarAcao() {
        Localizacao destino = getLocalizacaoDestino();
        if (destino != null) {
            // Calcula a próxima localização com base no destino
            Localizacao proximaLocalizacao = getLocalizacaoAtual().proximaLocalizacao(localizacaoDestino);
            setLocalizacaoAtual(proximaLocalizacao); // Atualiza a localização atual
        }
    }
}