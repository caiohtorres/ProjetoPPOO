import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Classe responsável pela simulação de um restaurante com filas de espera e mesas disponíveis.
 */
public class Simulacao {
    private Mapa mapa;
    private JanelaSimulacao janelaSimulacao;
    private Restaurante restaurante;

    private int colunaFila = 0;
    private int linhaFilaVip = 5;
    private int linhaFilaEco = 5;
    private int linhaFilaFamilia = 5;

    /**
     * Construtor que inicializa o mapa, restaurante e mesas.
    */
    public Simulacao() {
        mapa = new Mapa();

        int id = 0;

        restaurante = Restaurante.getInstance("Restaurante do Leo");

        // Adicionar mesas VIP (2 mesas)
        adicionarMesasVIP(id, 0, 0); 
        id += 2;

        // Adicionar mesas Família (3 mesas)
        adicionarMesasFamilia(id, 0, 6);
        id += 3;

        // Adicionar mesas Econômicas (5 mesas)
        adicionarMesasEconomicas(id, 2, 0);

        janelaSimulacao = new JanelaSimulacao(mapa);
    }

    /**
     * Adiciona mesas VIP ao restaurante.
     * @param id Identificador das mesas
     * @param linha Linha onde as mesas serão colocadas
     * @param colunaInicial Coluna inicial das mesas
     */
    private void adicionarMesasVIP(int id, int linha, int colunaInicial) {
        for (int i = 0; i <= 1; i++) {
            Localizacao localizacao = new Localizacao(colunaInicial + i * 3, linha); // Adiciona 2 blocos de distância
            Mesa mesaVIP = new MesaVIP(id++, 100.0, localizacao);
            restaurante.adicionarMesa(mesaVIP);
            mapa.adicionarItem(mesaVIP);
        }
    }

    /**
     * Adiciona mesas familiares ao restaurante.
     * @param id Identificador das mesas
     * @param linha Linha onde as mesas serão colocadas
     * @param colunaInicial Coluna inicial das mesas
     */
    private void adicionarMesasFamilia(int id, int linha, int colunaInicial) {
        for (int i = 0; i <= 2; i++) {
            Localizacao localizacao = new Localizacao(colunaInicial + i * 3, linha); // Adiciona 2 blocos de distância
            Mesa mesaFamilia = new MesaFamilia(id++, 70.0, localizacao);
            restaurante.adicionarMesa(mesaFamilia);
            mapa.adicionarItem(mesaFamilia);
        }
    }

    /**
     * Adiciona mesas econômicas ao restaurante.
     * @param id Identificador das mesas
     * @param linhaInicial Linha inicial das mesas
     * @param colunaInicial Coluna inicial das mesas
     */
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

    /**
     * Verifica a disponibilidade de mesas de um determinado tipo.
     * @param tipoMesa Tipo de mesa a ser verificado
     * @return A mesa disponível, ou null caso nenhuma mesa esteja disponível
     */
    public Mesa verificarMesas(String tipoMesa) {
        ArrayList<Mesa> mesas = restaurante.getMesas(); 

        for (Mesa mesa : mesas) {
            if (mesa.isDisponivel() && mesa.getClass().getName().equals(tipoMesa)) { 
                System.out.println("Mesa disponível!");
                System.out.println("ID da Mesa: " + mesa.getId());
                return mesa;
            }
        }
        return null; 
    }

    /**
     * Realiza uma reserva para um cliente em espera.
     * @param clienteEspera Cliente que está na fila de espera
     * @param mesaDesejada Mesa desejada para a reserva
     */
    public void fazerReserva(ClienteEspera clienteEspera, Mesa mesaDesejada) {
        Random rand = new Random();
        int tempoReserva = rand.nextInt(50, 100);
        Cliente cliente = restaurante.retirarDaFilaEspera(clienteEspera);
        Reserva reserva = new Reserva(cliente, mesaDesejada, tempoReserva);
        cliente.setReserva(reserva);
        reserva.confirmarReserva(mesaDesejada);
        mapa.adicionarCliente(cliente);
        restaurante.adicionarNaFilaAtendidos(cliente);
        restaurante.setCaixaTotal(reserva.getValorTotal());
        System.out.printf(
                "| Cliente: %s | Mesa: %d \n| Pedidos:\n%s \n| Tempo de Reserva: %d minutos%n",
                cliente.getNome(),
                mesaDesejada.getId(),
                reserva.getPedidos(),
                reserva.getTempoReserva());
        System.out.println("Mesa disponível!");
        System.out.println(mesaDesejada.getId());
        
    }

    /**
     * Gera um novo cliente com uma certa probabilidade.
     */
    public void gerarCliente() {
        Random rand = new Random();
        int probabilidade = rand.nextInt(100);
        
        if (probabilidade < 20) {
            processarNovoCliente(rand.nextInt(3));
        }
    }

   /**
     * Atualiza a posição na fila de espera para um determinado tipo de mesa.
     * @param tipo Tipo de mesa para qual a fila será atualizada
     */
    private void atualizarPosicaoFila(TipoMesa tipo) {
        switch (tipo) {
            case VIP:
                linhaFilaVip += 1;
                if (linhaFilaVip == 15) {
                    colunaFila += 1;
                    linhaFilaVip = 5;
                }
                break;
            case FAMILIA:
                linhaFilaFamilia += 1;
                if (linhaFilaFamilia == 15) {
                    colunaFila += 1;
                    linhaFilaFamilia = 5;
                }
                break;
            case ECONOMICA:
                linhaFilaEco += 1;
                if (linhaFilaEco == 15) {
                    colunaFila += 1;
                    linhaFilaEco = 5;
                }
                break;
        }
    }

    /**
     * Processa a criação e inicialização de um novo cliente.
     * @param tipoMesa Indice do tipo de mesa desejado pelo cliente
     */
    private void processarNovoCliente(int tipoMesa) {
        TipoMesa[] tipos = TipoMesa.values();
        TipoMesa tipoSelecionado = tipos[tipoMesa];
        
        atualizarPosicaoFila(tipoSelecionado);
        Localizacao localizacao = criarLocalizacao(tipoSelecionado);
        Cliente novoCliente = new Cliente("Cliente Fulano", tipoSelecionado.nome, localizacao);
        ClienteEspera clienteEspera = new ClienteEspera(novoCliente, tipoSelecionado.nome);
        
        processarReserva(clienteEspera);
    }

    /**
     * Cria uma nova localização para um cliente baseado no tipo de mesa.
     * @param tipo Tipo de mesa para determinar a localização
     * @return Nova localização calculada para o cliente
     */
    private Localizacao criarLocalizacao(TipoMesa tipo) {
        int coluna = colunaFila + tipo.deslocamentoColuna;
        int linha = switch (tipo) {
            case VIP -> linhaFilaVip;
            case FAMILIA -> linhaFilaFamilia;
            case ECONOMICA -> linhaFilaEco;
        };
        return new Localizacao(coluna, linha);
    }

    /**
     * Processa a tentativa de reserva para um cliente em espera.
     * @param clienteEspera Cliente que tentará fazer a reserva
     */
    private void processarReserva(ClienteEspera clienteEspera) {
        Mesa mesaDisponivel = verificarMesas(clienteEspera.getTipoMesa());
        
        if (mesaDisponivel != null) {
            fazerReserva(clienteEspera, mesaDisponivel);
        } else {
            restaurante.adicionarNaFilaEspera(clienteEspera);
            mapa.adicionarCliente(clienteEspera.getRepresentante());
        }
    }

    private enum TipoMesa {
        VIP("MesaVIP", 0),
        FAMILIA("MesaFamilia", 4),
        ECONOMICA("MesaEconomica", 8);
        
        final String nome;
        final int deslocamentoColuna;
        
        TipoMesa(String nome, int deslocamentoColuna) {
            this.nome = nome;
            this.deslocamentoColuna = deslocamentoColuna;
        }
    }

    /**
     * Executa a simulação por um número determinado de passos.
     * @param numPassos Número de passos da simulação a serem executados
     */
    public void executarSimulacao(int numPassos) {
        janelaSimulacao.executarAcao();
        System.out.println("Bem vindos ao " + restaurante.getRestauranteNome());
        // Integer coluna = 0;
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            gerarCliente();
            esperar(500);

        }
        System.out.println("Valor total do caixa do restaurante: " + restaurante.getCaixaTotal());
    }

    /**
     * Executa um único passo da simulação, processando todos os clientes e mesas.
     */
    private void executarUmPasso() {
        janelaSimulacao.executarAcao();

        ArrayList<ClienteEspera> filaEspera = restaurante.getFilaEspera();
        ArrayList<Cliente> filaAtendidos = restaurante.getFilaAtendidos();

        processarFilaEspera(filaEspera);

        if (!filaAtendidos.isEmpty()) {
            for (Cliente clienteAtendido : filaAtendidos) {
                Reserva reserva = clienteAtendido.getReserva();
                if (reserva != null) {
                    Mesa mesa = reserva.getMesa();
                    if (!mesa.isDisponivel()) {
                        definirDestinoCliente(clienteAtendido, mesa);
                        mesa.setDisponibilidade(false);
                    }
                }

                clienteAtendido.atualizarLocalizacao();
                clienteAtendido.executarAcao();
            }
        }
        simularPasso();
    }

    /**
     * Processa todos os clientes na fila de espera, verificando disponibilidade de mesas.
     * @param fila Lista de clientes em espera a ser processada
     */
    private void processarFilaEspera(ArrayList<ClienteEspera> fila) {
        if (!fila.isEmpty()) {
            Iterator<ClienteEspera> it = fila.iterator();
            while (it.hasNext()) {
                ClienteEspera clienteEspera = it.next();
                String tipoMesa = clienteEspera.getTipoMesa(); 
                Mesa mesaDisponivel = verificarMesas(tipoMesa);

                if (mesaDisponivel != null && mesaDisponivel.isDisponivel()) {
                    fazerReserva(clienteEspera, mesaDisponivel);
                    it.remove();
                }
            }
        }
    }

    /**
     * Define o destino de um cliente em relação à mesa reservada.
     * @param cliente Cliente que terá seu destino definido
     * @param mesa Mesa associada ao cliente
     */
    private void definirDestinoCliente(Cliente cliente, Mesa mesa) {
        Localizacao localizacaoMesa = mesa.getLocalizacaoMesa();
        Localizacao destino = new Localizacao(localizacaoMesa.getX() + 1, localizacaoMesa.getY());
        cliente.setLocalizacaoDestino(destino);
    }

    /**
     * Simula um passo do restaurante, processando todas as reservas ativas.
     */
    public void simularPasso() {
        ArrayList<Cliente> clientes = restaurante.getFilaAtendidos();
        for (Cliente cliente : clientes) {
            if (cliente != null && cliente.getReserva() != null) {
                processarReserva(cliente);
            }
        }
    }

    /**
     * Processa uma reserva específica, atualizando seu tempo ou finalizando-a.
     * @param cliente Cliente cuja reserva será processada
     */
    private void processarReserva(Cliente cliente) {
        Reserva reserva = cliente.getReserva();
        if (reserva.getTempoReserva() > 0) {
            reserva.setTempoReserva();
        } else {
            finalizarReserva(cliente, reserva);
        }
    }

    /**
     * Finaliza uma reserva, liberando a mesa e atualizando o estado do cliente.
     * @param cliente Cliente cuja reserva será finalizada
     * @param reserva Reserva a ser finalizada
     */
    private void finalizarReserva(Cliente cliente, Reserva reserva) {
        System.out.println("Reserva finalizada! : " + cliente.getId());
        cliente.setReserva(null);
        reserva.getMesa().setDisponibilidade(true);
        cliente.setLocalizacaoDestino(new Localizacao(20, 20));
    }

    /**
     * Pausa a execução por um determinado período.
     * @param milisegundos Tempo em milissegundos para pausar a execução
     */
    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
