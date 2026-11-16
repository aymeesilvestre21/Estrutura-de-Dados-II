package ed2.projeto.algoritmosordenacao;

public class VisualizadorOrdenacao {

  private static final int PAUSA_MS = 150;

  public static void main(String[] args) {
    int[] vetor = {6, 2, 5, 1, 4, 3};
    int[] passo = {0};

    System.out.println("Demonstracao visual: Bubble Sort");
    System.out.println("Cada # representa uma unidade; um quadro e exibido apos cada troca.");
    System.out.println("\nEstado inicial:");
    imprimirBarras(vetor);

    new BubbleSort().ordenarVetorComPassos(vetor, estado -> {
      passo[0]++;
      System.out.printf("%nPasso %d:%n", passo[0]);
      imprimirBarras(estado);
      aguardarAnimacao();
    });

    System.out.println("\nResultado final:");
    imprimirBarras(vetor);
    System.out.println("Ordenado corretamente: " + (Utils.estaOrdenado(vetor) ? "SIM" : "NAO"));
  }

  private static void imprimirBarras(int[] vetor) {
    for (int valor : vetor) {
      StringBuilder barra = new StringBuilder();
      for (int unidade = 0; unidade < valor; unidade++) {
        barra.append('#');
      }
      System.out.printf("%2d | %s%n", valor, barra);
    }
  }

  private static void aguardarAnimacao() {
    try {
      Thread.sleep(PAUSA_MS);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new IllegalStateException("A animacao foi interrompida.", e);
    }
  }
}
