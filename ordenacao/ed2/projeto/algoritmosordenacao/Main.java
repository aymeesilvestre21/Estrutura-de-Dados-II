package ed2.projeto.algoritmosordenacao;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {

  private static final String[] ARQUIVOS_PADRAO = {
    "entrada_pequena.txt",
    "entrada_grande.txt",
    "numeros_repetidos.txt",
    "numeros_negativos.txt",
    "vetor_ordenado.txt",
    "vetor_inverso.txt"
  };

  public static void main(String[] args) {
    if (args.length > 0 && args[0].equalsIgnoreCase("--testar")) {
      executarBateriaDeTestesEtapa8();
      return;
    }

    Scanner scanner = new Scanner(System.in);
    String arquivoAtual = ARQUIVOS_PADRAO[0];
    int[] vetorOriginal = carregarArquivoComFallback(arquivoAtual, scanner);

    boolean executando = true;
    while (executando) {
      exibirMenuPrincipal(arquivoAtual, vetorOriginal.length);
      System.out.print("Opcao: ");
      String entrada = scanner.nextLine().trim();

      int opcao;
      try {
        opcao = Integer.parseInt(entrada);
      } catch (NumberFormatException e) {
        System.out.println("Opcao invalida. Digite um numero de 0 a 7.");
        continue;
      }

      switch (opcao) {
        case 1:
          executarAlgoritmo(new BubbleSort(), vetorOriginal);
          break;
        case 2:
          executarAlgoritmo(new InsertionSort(), vetorOriginal);
          break;
        case 3:
          executarAlgoritmo(new SelectionSort(), vetorOriginal);
          break;
        case 4:
          executarAlgoritmo(new MergeSort(), vetorOriginal);
          break;
        case 5:
          executarAlgoritmo(new QuickSort(), vetorOriginal);
          break;
        case 6:
          testarTodosNoArquivoAtual(vetorOriginal, arquivoAtual);
          break;
        case 7:
          String novoArquivo = selecionarArquivoDeEntrada(scanner, arquivoAtual);
          try {
            vetorOriginal = Utils.lerVetorDeArquivo(novoArquivo);
            arquivoAtual = novoArquivo;
            System.out.println("Arquivo carregado: " + arquivoAtual + " (" + vetorOriginal.length + " numeros)");
          } catch (Exception e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
          }
          break;
        case 0:
          executando = false;
          System.out.println("Programa encerrado.");
          break;
        default:
          System.out.println("Opcao invalida. Escolha entre 0 e 7.");
      }
    }

    scanner.close();
  }

  private static void exibirMenuPrincipal(String arquivoAtual, int quantidadeElementos) {
    System.out.println("\n--- MENU DE ORDENACAO ---");
    System.out.println("Arquivo atual: " + arquivoAtual + " (" + quantidadeElementos + " numeros)\n");
    System.out.println("1 - Bubble Sort");
    System.out.println("2 - Insertion Sort");
    System.out.println("3 - Selection Sort");
    System.out.println("4 - Merge Sort");
    System.out.println("5 - Quick Sort");
    System.out.println("6 - Testar todos os algoritmos");
    System.out.println("7 - Trocar arquivo .txt");
    System.out.println("0 - Sair\n");
  }

  private static void testarTodosNoArquivoAtual(int[] vetorOriginal, String nomeArquivo) {
    IAlgoritmoOrdenacao[] algoritmos = {
      new BubbleSort(),
      new InsertionSort(),
      new SelectionSort(),
      new MergeSort(),
      new QuickSort()
    };

    System.out.println("\n>>> Teste comparativo: " + nomeArquivo + " (" + vetorOriginal.length + " itens)");
    System.out.println("Antes:");
    Utils.imprimirVetor(vetorOriginal);

    int[] referencia = null;
    for (IAlgoritmoOrdenacao algoritmo : algoritmos) {
      int[] copia = Utils.clonarVetor(vetorOriginal);
      long inicioNs = System.nanoTime();
      algoritmo.ordenarVetor(copia);
      long tempoNs = System.nanoTime() - inicioNs;

      if (referencia == null) {
        referencia = copia;
      }

      System.out.printf(
        Locale.US,
        "  %-15s : %.4f ms (%d ns)%n",
        algoritmo.toString(),
        tempoNs / 1_000_000.0,
        tempoNs
      );
    }

    if (referencia != null) {
      System.out.println("Depois:");
      Utils.imprimirVetor(referencia);
    }
  }

  private static void executarAlgoritmo(IAlgoritmoOrdenacao algoritmo, int[] vetorOriginal) {
    int[] vetorCopia = Utils.clonarVetor(vetorOriginal);

    System.out.println("\n>>> " + algoritmo.toString());
    System.out.println("Antes:");
    Utils.imprimirVetor(vetorCopia);

    long inicioNs = System.nanoTime();
    algoritmo.ordenarVetor(vetorCopia);
    long tempoNs = System.nanoTime() - inicioNs;
    double tempoMs = tempoNs / 1_000_000.0;

    System.out.println("Depois:");
    Utils.imprimirVetor(vetorCopia);
    System.out.printf(Locale.US, "Tempo: %.4f ms (%d ns)%n", tempoMs, tempoNs);
  }

  private static String selecionarArquivoDeEntrada(Scanner scanner, String arquivoAtual) {
    System.out.println("\n--- Escolher Arquivo .txt ---");
    for (int i = 0; i < ARQUIVOS_PADRAO.length; i++) {
      System.out.println((i + 1) + " - " + ARQUIVOS_PADRAO[i]);
    }
    System.out.println("7 - Digitar outro caminho de arquivo .txt");
    System.out.print("Opcao: ");

    String escolha = scanner.nextLine().trim();
    try {
      int indice = Integer.parseInt(escolha);
      if (indice >= 1 && indice <= ARQUIVOS_PADRAO.length) {
        return ARQUIVOS_PADRAO[indice - 1];
      } else if (indice == 7) {
        System.out.print("Caminho do arquivo .txt: ");
        return scanner.nextLine().trim();
      }
    } catch (NumberFormatException ignored) {
    }
    return arquivoAtual;
  }

  private static int[] carregarArquivoComFallback(String caminhoInicial, Scanner scanner) {
    try {
      return Utils.lerVetorDeArquivo(caminhoInicial);
    } catch (FileNotFoundException e) {
      while (true) {
        System.out.print("Digite o caminho do arquivo .txt: ");
        String caminho = scanner.nextLine().trim();
        try {
          return Utils.lerVetorDeArquivo(caminho);
        } catch (Exception ex) {
          System.out.println("Erro: " + ex.getMessage());
        }
      }
    }
  }

  public static void executarBateriaDeTestesEtapa8() {
    IAlgoritmoOrdenacao[] algoritmos = {
      new BubbleSort(),
      new InsertionSort(),
      new SelectionSort(),
      new MergeSort(),
      new QuickSort()
    };

    for (String nomeArquivo : ARQUIVOS_PADRAO) {
      try {
        int[] vetorOriginal = Utils.lerVetorDeArquivo(nomeArquivo);
        System.out.println("\n--- Teste: " + nomeArquivo + " (" + vetorOriginal.length + " itens) ---");
        System.out.println("Entrada:");
        Utils.imprimirVetor(vetorOriginal);

        int[] referencia = null;
        boolean todosIguais = true;

        for (IAlgoritmoOrdenacao algoritmo : algoritmos) {
          int[] copia = Utils.clonarVetor(vetorOriginal);
          long inicioNs = System.nanoTime();
          algoritmo.ordenarVetor(copia);
          long tempoNs = System.nanoTime() - inicioNs;

          if (referencia == null) {
            referencia = copia;
          } else if (!Arrays.equals(referencia, copia)) {
            todosIguais = false;
          }

          System.out.printf(
            Locale.US,
            "  %-15s | Ordenado: %-3s | %.4f ms (%d ns)%n",
            algoritmo.toString(),
            Utils.estaOrdenado(copia) ? "SIM" : "NAO",
            tempoNs / 1_000_000.0,
            tempoNs
          );
        }

        if (referencia != null) {
          System.out.println("Saida:");
          Utils.imprimirVetor(referencia);
          System.out.println("Resultados identicos: " + (todosIguais ? "SIM" : "NAO"));
        }
      } catch (Exception e) {
        System.out.println("Erro em " + nomeArquivo + ": " + e.getMessage());
      }
    }
  }

}
