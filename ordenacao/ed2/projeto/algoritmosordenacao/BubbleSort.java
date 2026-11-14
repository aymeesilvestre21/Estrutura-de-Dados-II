
package ed2.projeto.algoritmosordenacao;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 
 * @author Hilario Tomaz Alves de Oliveira
 *
 */

public class BubbleSort implements IAlgoritmoOrdenacao {

  @Override
	
  public void ordenarVetor(int[] vetor) {
    ordenar(vetor, null);
  }

  public void ordenarVetorComPassos(int[] vetor, Consumer<int[]> aoTrocar) {
    Objects.requireNonNull(aoTrocar, "aoTrocar");
    ordenar(vetor, aoTrocar);
  }

  private void ordenar(int[] vetor, Consumer<int[]> aoTrocar) {
    if (vetor == null || vetor.length <= 1) {
      return;
    }

    boolean isTroca;

    int tamanhoVetorMenosUm = vetor.length - 1;
	
    do {
		    	
	  isTroca = false;
		
	  for(int i = 0; i < tamanhoVetorMenosUm ; i++) {
		    	  
		if( vetor[i] > vetor[i + 1] ) {
		        	
		  int aux = vetor[i];
		  
		  vetor[i] = vetor[i + 1];
		  
		  vetor[i + 1] = aux;
		  
		  isTroca = true;

      if (aoTrocar != null) {
        aoTrocar.accept(Arrays.copyOf(vetor, vetor.length));
      }
		  
		}
		
	  } // FOR ELEMENTOS VETOR
		
    } while( isTroca );
	
  }
  
  @Override
	
  public String toString() {
	
    return "BubbleSort";
    
  }
	
}
