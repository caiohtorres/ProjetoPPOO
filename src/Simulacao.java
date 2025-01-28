import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Simulacao {
    private Mapa mapa;
    private JanelaSimulacao janelaSimulacao;
    private Restaurante restaurante;

    private int colunaFila = 0;
    private int linhaFila = 5;

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

    public void fazerReserva(ClienteEspera clienteEspera, Mesa mesaDesejada){
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
            reserva.getTempoReserva()
        );
        System.out.println("Mesa disponível!");
        System.out.println(mesaDesejada.getId());
    }

    public void atualizarFila(){
        linhaFila --;

        System.out.println("Posicao da fila" + linhaFila);
    }

    public void gerarCliente() {
        Random rand = new Random();
        int probabilidade = rand.nextInt(100);
        if (probabilidade < 20) {
            Localizacao localizacao = new Localizacao(colunaFila, linhaFila);
            linhaFila += 1;
            if (linhaFila == 10) {
                colunaFila += 1;
                linhaFila = 5;
                if (colunaFila == 8) {
                    linhaFila = 5;
                }
            }

            Mesa mesaDesejada = restaurante.getMesaAleatoria();
            Cliente novoCliente = new Cliente("Cliente Fulano", mesaDesejada, localizacao);
            ClienteEspera clienteEspera = new ClienteEspera(novoCliente, mesaDesejada);
            restaurante.adicionarNaFilaEspera(clienteEspera);

            if (!mesaDesejada.isDisponivel()) {
                System.out.println("Mesa não disponível para o cliente.");
                mapa.adicionarCliente(novoCliente);
            }else{
                fazerReserva(clienteEspera, mesaDesejada);
            }                    
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
            esperar(500);

        }
        System.out.println("Valor total do caixa do restaurante: " + restaurante.getCaixaTotal());
    }

    private void executarUmPasso() {
        janelaSimulacao.executarAcao();
        ArrayList<ClienteEspera> filaEspera = restaurante.getFilaEspera();
        ArrayList<Cliente> filaAtendidos = restaurante.getFilaAtendidos();
        if (!filaEspera.isEmpty()) {
            Iterator<ClienteEspera> it = filaEspera.iterator();
            while(it.hasNext()){
                ClienteEspera clienteEspera = it.next();
                Mesa mesa = clienteEspera.getTipoMesa();
                if (mesa.isDisponivel()) {
                    fazerReserva(clienteEspera, mesa);
                    it.remove();    
                }
            }
        }
        if (!filaAtendidos.isEmpty()) {
            for (Cliente clienteAtendido : filaAtendidos) {
                Mesa mesa = clienteAtendido.getTipoMesa();
                if (!mesa.isDisponivel() && clienteAtendido.getReserva() != null) {
                    definirDestinoCliente(clienteAtendido, mesa);
                    mesa.setDisponibilidade(false);
                }
                clienteAtendido.atualizarLocalizacao();
                clienteAtendido.executarAcao();
            }
        } 
        simularPasso();
        
    }

    private void definirDestinoCliente(Cliente cliente, Mesa mesa) {
        Localizacao localizacaoMesa = mesa.getLocalizacaoMesa();
        Localizacao destino = new Localizacao(localizacaoMesa.getX() + 1, localizacaoMesa.getY());
        cliente.setLocalizacaoDestino(destino);
    }

    public void simularPasso() {
        ArrayList<Cliente> clientes = restaurante.getFilaAtendidos();

        for (Cliente cliente : clientes) {
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
