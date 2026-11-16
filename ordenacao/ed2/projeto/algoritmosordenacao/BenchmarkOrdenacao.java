package ed2.projeto.algoritmosordenacao;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class BenchmarkOrdenacao {

  private static final int AQUECIMENTOS = 5;
  private static final int AMOSTRAS = 21;
  private static final int[] TAMANHOS = {10, 100, 1000};

  public static void main(String[] args) {
    IAlgoritmoOrdenacao[] algoritmos = {
      new BubbleSort(),
      new InsertionSort(),
      new SelectionSort(),
      new MergeSort(),
      new QuickSort()
    };

    System.out.println("Benchmark local dos algoritmos de ordenacao");
    System.out.println("Mediana de 21 amostras; clonagem dos vetores fora da medicao.");
    System.out.println("Entradas deterministicas embaralhadas; tempos por vetor.\n");

    for (int tamanho : TAMANHOS) {
      int[] entrada = criarVetorEmbaralhado(tamanho);
      int[] esperado = Arrays.copyOf(entrada, entrada.length);
      Arrays.sort(esperado);
      int repeticoes = repeticoesPorTamanho(tamanho);

      System.out.printf("Tamanho: %d | repeticoes por amostra: %d%n", tamanho, repeticoes);
      for (IAlgoritmoOrdenacao algoritmo : algoritmos) {
        for (int aquecimento = 0; aquecimento < AQUECIMENTOS; aquecimento++) {
          executarLote(algoritmo, entrada, esperado, repeticoes);
        }

        long[] tempos = new long[AMOSTRAS];
        for (int amostra = 0; amostra < AMOSTRAS; amostra++) {
          tempos[amostra] = executarLote(algoritmo, entrada, esperado, repeticoes);
        }

        Arrays.sort(tempos);
        double nanosPorVetor = (double) tempos[AMOSTRAS / 2] / repeticoes;
        System.out.printf(
          Locale.US,
          "  %-15s | mediana: %.4f ms por vetor%n",
          algoritmo.toString(),
          nanosPorVetor / 1_000_000.0
        );
      }
      System.out.println();
    }
  }

  private static long executarLote(
      IAlgoritmoOrdenacao algoritmo,
      int[] entrada,
      int[] esperado,
      int repeticoes) {
    int[][] vetores = new int[repeticoes][];
    for (int i = 0; i < repeticoes; i++) {
      vetores[i] = Arrays.copyOf(entrada, entrada.length);
    }

    long inicio = System.nanoTime();
    for (int[] vetor : vetores) {
      algoritmo.ordenarVetor(vetor);
    }
    long tempo = System.nanoTime() - inicio;

    for (int[] vetor : vetores) {
      if (!Arrays.equals(vetor, esperado)) {
        throw new IllegalStateException(
          algoritmo + " produziu um resultado incorreto para " + entrada.length + " elementos."
        );
      }
    }
    return tempo;
  }

  private static int[] criarVetorEmbaralhado(int tamanho) {
    int[] vetor = new int[tamanho];
    for (int i = 0; i < tamanho; i++) {
      vetor[i] = i;
    }

    Random random = new Random(20261114L + tamanho);
    for (int i = tamanho - 1; i > 0; i--) {
      int indice = random.nextInt(i + 1);
      int temporario = vetor[i];
      vetor[i] = vetor[indice];
      vetor[indice] = temporario;
    }
    return vetor;
  }

  private static int repeticoesPorTamanho(int tamanho) {
    if (tamanho <= 10) {
      return 1000;
    }
    if (tamanho <= 100) {
      return 100;
    }
    return 10;
  }
}
