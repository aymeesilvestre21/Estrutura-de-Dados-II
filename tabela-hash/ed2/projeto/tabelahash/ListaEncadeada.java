
package ed2.projeto.tabelahash;
/* por: Jarde Alverga */



// Classe ListaEncadeada
public class ListaEncadeada {

    // Atributos do objeto
    private No primeiro;

    // Construtor
    public ListaEncadeada() {
        this.primeiro = null;
    }

    // Método de acesso ao primeiro nó
    public No getPrimeiro() {
        return primeiro;
    }

// metodo para inserir un aluno no final da lista

public void inserirNoFinal(Aluno aluno) {
    No novo = new No(aluno);

    // Se a lista estiver vazia
    if (primeiro == null) {
        primeiro = novo;
        return;
    }

    // Percorre a lista até o último nó
    No atual = primeiro;

    while (atual.getProximo() != null) {
        atual = atual.getProximo();
    }

    // Liga o último nó ao novo nó
    atual.setProximo(novo);
}


    // Método para remover um aluno pela matrícula
    public boolean remover(int matricula) {

        // Verifica se a lista está vazia
        if (primeiro == null) {
            return false;
        }

        // Verifica se o aluno está no primeiro nó
        if (primeiro.getAluno().getMatricula() == matricula) {
            primeiro = primeiro.getProximo();
            return true;
        }

        // Percorre a lista procurando o aluno
        No atual = primeiro;

        while (atual.getProximo() != null) {

            if (atual.getProximo().getAluno().getMatricula() == matricula) {

                // Desliga o nó que será removido
                atual.setProximo(atual.getProximo().getProximo());
                return true;
            }

            atual = atual.getProximo();
        }

        return false;
    }


    // Método para percorrer a lista
    public void exibir() {
        No atual = primeiro;

        while (atual != null) {
            System.out.println(atual.getAluno());
            atual = atual.getProximo();
        }
    }
}
