import java.util.Random;
/**
 * Responsavel pela simulacao.
 * @author David J. Barnes and Michael Kolling and Luiz Merschmann
 */

public class Simulacao {
    private Mapa mapa;
    private JanelaSimulacao janelaSimulacao;

    public Simulacao() {
        Random rand = new Random(12345);
        mapa = new Mapa();
        int largura = mapa.getLargura();
        int altura = mapa.getAltura();

        int id = 1;

        if (largura <= 0 || altura <= 0) {
            throw new IllegalArgumentException("O mapa deve ter largura e altura maiores que zero.");
        }

        // Adiciona 10 mesas Econômicas
        for (int i = 0; i < 10; i++) {
            MesaEconomica mesaEconomica = new MesaEconomica(id++, 50.0, 10,  new Localizacao(rand.nextInt(largura), rand.nextInt(altura)));
            mapa.adicionarItem(mesaEconomica);
        }

        // Adiciona 5 mesas Família
        for (int i = 0; i < 5; i++) {
            MesaFamilia mesaFamilia = new MesaFamilia(
            id++, 70.0, new Localizacao(rand.nextInt(largura), rand.nextInt(altura)));
            mapa.adicionarItem(mesaFamilia);
        }

        // Adiciona 3 mesas VIP
        for (int i = 0; i < 3; i++) {
            MesaVIP mesaVIP = new MesaVIP(id++, 100.0,50.0,new Localizacao(rand.nextInt(largura), rand.nextInt(altura)));
            mapa.adicionarItem(mesaVIP);
        }

        janelaSimulacao = new JanelaSimulacao(mapa);
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
