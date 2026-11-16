package ed2.projeto.tabelahash;
/*por: Jarde Alverga */
// Classe Aluno
public class Aluno {

    // Atributos do objeto
    private int matricula;
    private String nome;
    private int idade;

    // Construtor
    public Aluno(int matricula, String nome, int idade) {
        this.matricula = matricula;
        this.nome = nome;
        this.idade = idade;
    }

    // Métodos de acesso
    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    // Exibição do objeto
    @Override
    public String toString() {
        return "Matrícula: " + matricula
             + " | Nome: " + nome
             + " | Idade: " + idade;
    }
}