
package ed2.projeto.tabelahash;
/* por: Jarde Alverga */


// Classe TabelaHash
public class TabelaHash {

    // atributos do objeto
    private ListaEncadeada[] tabela;

    // construtor
    public TabelaHash() {

        tabela = new ListaEncadeada[11];

        // Inicializa as 11 listas encadeadas
        for (int i = 0; i < tabela.length; i++) {
            tabela[i] = new ListaEncadeada();
        }
    }

    // metodo para calcular a posicao na tabela
    public int hash(int matricula) {

        // verifica se a matricula é valida
        if (matricula < 0) {
            throw new IllegalArgumentException(
                "A matricula nao pode ser negativa"
            );
        }

        // Retorna a posição de 0 a 10
        return matricula % 11;
    }

}
