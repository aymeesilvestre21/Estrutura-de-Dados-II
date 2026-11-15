package ed2.projeto.tabelahash;

public class TabelaHash {

    private static final int TAMANHO = 11;
    private final ListaEncadeada[] tabela;

    public TabelaHash() {
        tabela = new ListaEncadeada[TAMANHO];
        for (int i = 0; i < tabela.length; i++) {
            tabela[i] = new ListaEncadeada();
        }
    }

    // Calcula o endereco da matricula. Rejeita matriculas negativas.
    public int hash(int matricula) {
        if (matricula < 0) {
            throw new IllegalArgumentException("Matricula invalida: deve ser maior ou igual a zero.");
        }
        return matricula % TAMANHO;
    }

    // Insere no final da lista e rejeita matriculas duplicadas.
    public boolean inserir(int matricula, String nome, int idade) {
        int endereco = hash(matricula);
        if (buscar(matricula) != null) {
            return false;
        }
        tabela[endereco].inserirNoFinal(new Aluno(matricula, nome, idade));
        return true;
    }

    // Devolve o no com a matricula ou null se nao existir.
    public No buscar(int matricula) {
        int endereco = hash(matricula);
        for (No atual = tabela[endereco].getPrimeiro(); atual != null; atual = atual.getProximo()) {
            if (atual.getAluno().getMatricula() == matricula) {
                return atual;
            }
        }
        return null;
    }

    // Remove o aluno com a matricula, se existir.
    public boolean remover(int matricula) {
        return tabela[hash(matricula)].remover(matricula);
    }
}
