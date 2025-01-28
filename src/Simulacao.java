import java.util.Random;
import java.util.ArrayList;
import java.util.Queue;

public class Simulacao {
    private Mapa mapa;
    private JanelaSimulacao janelaSimulacao;
    private Restaurante restaurante;

    private int coluna = 0;
    private int linha = 5;

    public Simulacao() {
        Random rand = new Random(12345);
        mapa = new Mapa();
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();

        int id = 0;
        Integer idCliente = 0;
        restaurante = new Restaurante("Restaurante do Léo", new Localizacao(10, 10));

        // Adicionar mesas VIP (2 mesas)
        adicionarMesasVIP(id, 0, 0); // Colocando na primeira linha
        id += 1;

        // Adicionar mesas Família (3 mesas)
        adicionarMesasFamilia(id, 0, 6); // Colocando nas 4 próximas posições
        id += 2;

        // Adicionar mesas Econômicas (5 mesas)
        adicionarMesasEconomicas(id, 2, 0); // Colocando nas linhas abaixo
        id += 4;

        janelaSimulacao = new JanelaSimulacao(mapa);

        adicionarParedes(restaurante, 4);
    }

    // Método para adicionar mesas VIP na primeira linha
    private void adicionarMesasVIP(int id, int linha, int colunaInicial) {
        for (int i = 0; i <= 1; i++) {
            Localizacao localizacao = new Localizacao(colunaInicial + i * 3, linha); // Adiciona 2 blocos de distância
            Mesa mesaVIP = new MesaVIP(id++, 100.0, localizacao);
            restaurante.adicionarMesa(mesaVIP);
            mapa.adicionarItem(mesaVIP);
        }
    }

    // Método para adicionar mesas Família na primeira linha
    private void adicionarMesasFamilia(int id, int linha, int colunaInicial) {
        for (int i = 0; i <= 2; i++) {
            Localizacao localizacao = new Localizacao(colunaInicial + i * 3, linha); // Adiciona 2 blocos de distância
            Mesa mesaFamilia = new MesaFamilia(id++, 70.0, localizacao);
            restaurante.adicionarMesa(mesaFamilia);
            mapa.adicionarItem(mesaFamilia);
        }
    }

    // Método para adicionar mesas Econômicas na linha abaixo
    private void adicionarMesasEconomicas(int id, int linhaInicial, int colunaInicial) {
        for (int i = 0; i <= 4; i++) {
            int linha = linhaInicial + i / 5; // 5 mesas por linha
            int coluna = colunaInicial + (i % 5) * 3; // Mesas espaçadas 2 blocos
            Localizacao localizacao = new Localizacao(coluna, linha);
            Mesa mesaEconomica = new MesaEconomica(id++, 50.0, 10, localizacao);
            restaurante.adicionarMesa(mesaEconomica);
            mapa.adicionarItem(mesaEconomica);
        }
    }

    public void adicionarParedes(Restaurante restaurante, int coluna) {
        for (int i = 0; i < 10; i++) {
            if (i != 0) {
                // mapa.adicionarParede(restaurante, i, coluna);
            }
        }
    }

    public Mesa verificarMesas() {
        ArrayList<Mesa> mesas = new ArrayList<>();
        mesas = restaurante.getMesas();

        for (Mesa mesa : mesas) {
            if (mesa.isDisponivel() == true) {
                System.out.println("Mesa disponível!");
                System.out.println(mesa.getId());
                return mesa;
            }
        }
        return null;
    }

    public void gerarCliente() {
        Random rand = new Random();
        int probabilidade = rand.nextInt(100);
        if (probabilidade < 20) {
            Localizacao localizacao = new Localizacao(coluna, linha);
            linha += 1;
            if (linha == 10) {
                coluna += 1;
                linha = 5;
                if (coluna == 8) {
                    linha = 5;
                }
            }

            Mesa mesaDisponivel = verificarMesas();
            if (mesaDisponivel == null) {
                System.out.println("Nenhuma mesa disponível para o cliente.");
                Cliente cliente = new Cliente("Cliente Fulano", null, localizacao);
                mapa.adicionarCliente(cliente);
                ClienteEspera clienteEspera = new ClienteEspera(cliente, null);

                return;
            }

            int tempoReserva = rand.nextInt(300, 400);
            Cliente cliente = new Cliente("Cliente Fulano", mesaDisponivel, localizacao);
            Reserva reserva = new Reserva(cliente, mesaDisponivel, tempoReserva);
            cliente.setReserva(reserva);
            mesaDisponivel.setDisponibilidade(false);

            mapa.adicionarCliente(cliente);

            ClienteEspera clienteEspera = new ClienteEspera(cliente, mesaDisponivel);
            restaurante.adicionarNaFila(clienteEspera);
            restaurante.setCaixaTotal(reserva.getValorTotal());
        }
    }

    // public void enviarProximoClienteParaMesa(int numeroMesa) {
    // if (!restaurante.getFilaEspera().isEmpty() && numeroMesa >= 0 && numeroMesa <
    // 3) {
    // if (veiculoAtual == null
    // ||
    // veiculoAtual.getLocalizacaoAtual().equals(veiculoAtual.getLocalizacaoDestino()))
    // {
    // veiculoAtual = restaurante.retirarDaFila.poll();
    // veiculoAtual.setLocalizacaoDestino(mesas[numeroMesa].getLocalizacaoAtual());
    // }
    // }
    // }

    public void executarSimulacao(int numPassos) {
        janelaSimulacao.executarAcao();
        // Integer coluna = 0;
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            gerarCliente();
            esperar(1000);

        }
        System.out.println("Valor total do caixa do restaurante: " + restaurante.getCaixaTotal());
    }

    private void executarUmPasso() {
        janelaSimulacao.executarAcao();
        Queue<ClienteEspera> filaEspera = restaurante.getFilaEspera();
        if (!filaEspera.isEmpty()) {
            for (ClienteEspera clienteEspera : filaEspera) {
                Cliente cliente = clienteEspera.getRepresentante();
                Mesa mesa = clienteEspera.getTipoMesa();
                if (!mesa.isDisponivel()) {
                    definirDestinoCliente(cliente, mesa);
                    mesa.setDisponibilidade(false);
                }
                simularPasso();
                cliente.atualizarLocalizacao();
                cliente.executarAcao();
            }
        }
    }

    private void definirDestinoCliente(Cliente cliente, Mesa mesa) {
        Localizacao localizacaoMesa = mesa.getLocalizacaoMesa();
        Localizacao destino = new Localizacao(localizacaoMesa.getX() + 1, localizacaoMesa.getY());
        cliente.setLocalizacaoDestino(destino);
    }

    public void simularPasso() {
        Queue<ClienteEspera> clientes = restaurante.getFilaEspera();
        for (ClienteEspera clienteEspera : clientes) {
            Cliente cliente = clienteEspera.getRepresentante();
            if (cliente != null && cliente.getReserva() != null) {
                processarReserva(cliente);
            }
        }
    }

    private void processarReserva(Cliente cliente) {
        Reserva reserva = cliente.getReserva();
        if (reserva.getTempoReserva() > 0) {
            reserva.setTempoReserva();
        } else {
            finalizarReserva(cliente, reserva);
        }
    }

    private void finalizarReserva(Cliente cliente, Reserva reserva) {
        System.out.println("Reserva finalizada! : " + cliente.getId());
        cliente.setReserva(null);
        reserva.getMesa().setDisponibilidade(true);
        cliente.setLocalizacaoDestino(new Localizacao(20, 20));
    }

    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
