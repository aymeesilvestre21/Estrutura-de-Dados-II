package ed2.projeto.algoritmosordenacao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Hilario Tomaz Alves de Oliveira
 *
 */

public class Utils {

  private static final int LIMITE_EXIBICAO = 100;

  private Utils() {

  }
  
  // Métodos
  
  public static void imprimirVetor(int[] vetor) {
    if (vetor == null) {
      System.out.println("  Vetor = {null}");
      return;
    }

    int totalElementos = vetor.length;
    int limite = Math.min(totalElementos, LIMITE_EXIBICAO);

    System.out.print( "  Vetor = {" );
	  
    for(int i = 0; i < limite; i++) {
    	
      System.out.print( vetor[i] );
      
      if( i < ( limite - 1 ) ) {
    	  
        System.out.print( ", " );
    	  
      }
      
    }

    if (totalElementos > LIMITE_EXIBICAO) {
      System.out.println( ", ...} (exibindo os " + LIMITE_EXIBICAO + " primeiros de " + totalElementos + " elementos)" );
    } else {
      System.out.println( "}" );
    }
    
  }

  public static int[] gerarVetor(int tamanhoVetor, int limiteSuperior) {
	
    Random random = new Random();
	
    int[] vetor = new int[tamanhoVetor];
	
    for(int i = 0; i < tamanhoVetor; i++) {
	    	
      int numero = random.nextInt( limiteSuperior );
	 
      vetor[i] = numero;
	  
    } // FOR VETOR
	    
    return vetor;
    
  }

  public static int[] lerVetorDeArquivo(String caminhoArquivo) throws FileNotFoundException {
    File arquivo = resolverArquivo(caminhoArquivo);
    List<Integer> numeros = new ArrayList<>();

    try (Scanner leitor = new Scanner(arquivo)) {
      leitor.useDelimiter("[,;\\s]+");
      while (leitor.hasNext()) {
        String token = leitor.next().trim();
        if (!token.isEmpty()) {
          numeros.add(Integer.parseInt(token));
        }
      }
    }

    int[] vetor = new int[numeros.size()];
    for (int i = 0; i < numeros.size(); i++) {
      vetor[i] = numeros.get(i);
    }

    return vetor;
  }

  public static File resolverArquivo(String caminhoArquivo) {
    File direto = new File(caminhoArquivo);
    if (direto.exists() && direto.isFile()) {
      return direto;
    }

    File emOrdenacaoDados = new File("ordenacao/dados/" + caminhoArquivo);
    if (emOrdenacaoDados.exists() && emOrdenacaoDados.isFile()) {
      return emOrdenacaoDados;
    }

    File emDados = new File("dados/" + caminhoArquivo);
    if (emDados.exists() && emDados.isFile()) {
      return emDados;
    }

    return direto;
  }

  public static int[] clonarVetor(int[] vetorOriginal) {
    if (vetorOriginal == null) {
      return new int[0];
    }
    return Arrays.copyOf(vetorOriginal, vetorOriginal.length);
  }

  public static boolean estaOrdenado(int[] vetor) {
    if (vetor == null || vetor.length <= 1) {
      return true;
    }
    for (int i = 0; i < vetor.length - 1; i++) {
      if (vetor[i] > vetor[i + 1]) {
        return false;
      }
    }
    return true;
  }

}
