import java.util.Random;

public class Simulacao {
    private Mapa mapa;
    private JanelaSimulacao janelaSimulacao;

    public Simulacao() {
        Random rand = new Random(12345);
        mapa = new Mapa();
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();

        int id = 0;

        // Adicionar mesas VIP (2 mesas)
        adicionarMesasVIP(id, 0, 0);  // Colocando na primeira linha
        id += 2;

        // Adicionar mesas Família (3 mesas)
        adicionarMesasFamilia(id, 0, 4); // Colocando nas 4 próximas posições
        id += 3;

        // Adicionar mesas Econômicas (5 mesas)
        adicionarMesasEconomicas(id, 2, 0); // Colocando nas linhas abaixo
        id += 5;

        janelaSimulacao = new JanelaSimulacao(mapa);
    }

    // Método para adicionar mesas VIP na primeira linha
    private void adicionarMesasVIP(int id, int linha, int colunaInicial) {
        for (int i = 0; i <= 1; i++) {
            Localizacao localizacao = new Localizacao(colunaInicial + i * 2, linha); // Adiciona 2 blocos de distância
            MesaVIP mesaVIP = new MesaVIP(id++, 100.0, 50.0, localizacao);
            mapa.adicionarItem(mesaVIP);
        }
    }

    // Método para adicionar mesas Família na primeira linha
    private void adicionarMesasFamilia(int id, int linha, int colunaInicial) {
        for (int i = 0; i <= 2; i++) {
            Localizacao localizacao = new Localizacao(colunaInicial + i * 2, linha); // Adiciona 2 blocos de distância
            MesaFamilia mesaFamilia = new MesaFamilia(id++, 70.0, localizacao);
            mapa.adicionarItem(mesaFamilia);
        }
    }

    // Método para adicionar mesas Econômicas na linha abaixo
    private void adicionarMesasEconomicas(int id, int linhaInicial, int colunaInicial) {
        for (int i = 0; i <= 4; i++) {
            int linha = linhaInicial + i / 5; // 5 mesas por linha
            int coluna = colunaInicial + (i % 5) * 2; // Mesas espaçadas 2 blocos
            Localizacao localizacao = new Localizacao(coluna, linha);
            MesaEconomica mesaEconomica = new MesaEconomica(id++, 50.0, 10, localizacao);
            mapa.adicionarItem(mesaEconomica);
        }
    }

    public void executarSimulacao(int numPassos) {
        janelaSimulacao.executarAcao();
        for (int i = 0; i < numPassos; i++) {
            executarUmPasso();
            esperar(100);
        }
    }

    private void executarUmPasso() {
        janelaSimulacao.executarAcao();
    }

    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
