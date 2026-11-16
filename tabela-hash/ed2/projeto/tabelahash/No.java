package ed2.projeto.tabelahash;

/* por: Jarde Alverga */
public class No {

    // atributos do objeto
    private Aluno aluno;
    private No proximo;

    // construtor
    public No(Aluno aluno) {
        this.aluno = aluno;
        this.proximo = null;
    }

    // metodos de acesso
    public Aluno getAluno() {
        return aluno;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }

}
