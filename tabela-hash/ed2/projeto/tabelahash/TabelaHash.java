package ed2.projeto.tabelahash;

public class TabelaHash {

    private static final int TAMANHO = 11;

    // Provisorio: sera substituido pelas classes Aluno e No (etapas 11 e 12).
    static class No {
        int matricula;
        String nome;
        int idade;
        No proximo;

        No(int matricula, String nome, int idade) {
            this.matricula = matricula;
            this.nome = nome;
            this.idade = idade;
        }
    }

    private No[] tabela = new No[TAMANHO];

    // Calcula o endereco da matricula. Lanca IllegalArgumentException se for negativa.
    public int hash(int matricula) {
        if (matricula < 0) {
            throw new IllegalArgumentException("Matricula invalida: deve ser maior ou igual a zero.");
        }
        return matricula % TAMANHO;
    }

    // Devolve true se inseriu e false se a matricula ja existe.
    // Em caso de colisao, insere no final da lista encadeada.
    public boolean inserir(int matricula, String nome, int idade) {
        int endereco = hash(matricula);
        No novo = new No(matricula, nome, idade);

        if (tabela[endereco] == null) {
            tabela[endereco] = novo;
            return true;
        }

        No atual = tabela[endereco];
        while (true) {
            if (atual.matricula == matricula) {
                return false;
            }
            if (atual.proximo == null) {
                atual.proximo = novo;
                return true;
            }
            atual = atual.proximo;
        }
    }

    // Devolve o no com a matricula ou null se nao existir.
    public No buscar(int matricula) {
        int endereco = hash(matricula);
        for (No atual = tabela[endereco]; atual != null; atual = atual.proximo) {
            if (atual.matricula == matricula) {
                return atual;
            }
        }
        return null;
    }

    // Remove o aluno com a matricula. Devolve true se removeu e false se nao existe.
    public boolean remover(int matricula) {
        int endereco = hash(matricula);
        No atual = tabela[endereco];
        if (atual == null) {
            return false;
        }
        if (atual.matricula == matricula) {
            tabela[endereco] = atual.proximo;
            return true;
        }
        No anterior = atual;
        atual = atual.proximo;
        while (atual != null) {
            if (atual.matricula == matricula) {
                anterior.proximo = atual.proximo;
                return true;
            }
            anterior = atual;
            atual = atual.proximo;
        }
        return false;
    }
}
