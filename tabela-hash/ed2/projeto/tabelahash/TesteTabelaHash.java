package ed2.projeto.tabelahash;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

// Testes executaveis sem bibliotecas externas e sem depender da opcao -ea.
public class TesteTabelaHash {

    public static void main(String[] args) {
        testarSemColisao();
        testarColisoesEDuplicatas();
        testarRemocoes();
        testarExibicao();
        System.out.println("Todos os testes da Tabela Hash passaram.");
    }

    private static void verificar(boolean condicao, String mensagem) {
        if (!condicao) {
            throw new AssertionError(mensagem);
        }
    }

    private static void verificarLista(TabelaHash tabela, int... matriculas) {
        No atual = tabela.buscar(matriculas[0]);
        for (int matricula : matriculas) {
            verificar(atual != null, "Lista terminou antes do esperado.");
            verificar(atual.getAluno().getMatricula() == matricula, "Ordem incorreta na lista.");
            atual = atual.getProximo();
        }
        verificar(atual == null, "Lista contem nos extras ou duplicados.");
    }

    private static void testarSemColisao() {
        TabelaHash tabela = new TabelaHash();
        for (int i = 0; i < 11; i++) {
            verificar(tabela.inserir(i, "Aluno " + i, 20 + i), "Falha na insercao sem colisao.");
            verificar(tabela.hash(i) == i, "Posicao hash incorreta.");
        }
        for (int i = 0; i < 11; i++) {
            No encontrado = tabela.buscar(i);
            verificar(encontrado != null, "Aluno existente nao encontrado.");
            verificar(encontrado.getAluno().getNome().equals("Aluno " + i), "Nome incorreto.");
            verificar(encontrado.getAluno().getIdade() == 20 + i, "Idade incorreta.");
            verificarLista(tabela, i);
        }
        verificar(tabela.buscar(99) == null, "Busca inexistente deveria retornar null.");
        System.out.println("OK: insercoes sem colisao e buscas.");
    }

    private static void testarColisoesEDuplicatas() {
        TabelaHash tabela = new TabelaHash();
        verificar(tabela.buscar(1) == null, "Busca em tabela vazia deveria retornar null.");
        for (int matricula : new int[] {1, 12, 23, 34}) {
            verificar(tabela.inserir(matricula, "Aluno " + matricula, 20), "Falha na insercao com colisao.");
        }
        verificarLista(tabela, 1, 12, 23, 34);
        for (int matricula : new int[] {1, 12, 23, 34}) {
            verificar(!tabela.inserir(matricula, "Substituto", 99), "Duplicata foi aceita.");
            verificar(tabela.buscar(matricula).getAluno().getNome().equals("Aluno " + matricula),
                    "Duplicata alterou os dados originais.");
            verificar(tabela.buscar(matricula).getAluno().getIdade() == 20, "Duplicata alterou a idade.");
        }
        verificarLista(tabela, 1, 12, 23, 34);
        verificar(tabela.buscar(45) == null, "Busca inexistente em lista ocupada falhou.");
        System.out.println("OK: colisoes, insercao no final e rejeicao de duplicatas.");
    }

    private static void testarRemocoes() {
        // Cada cenario comeca com a mesma lista para isolar inicio, meio e fim.
        for (int removida : new int[] {1, 12, 23}) {
            TabelaHash tabela = new TabelaHash();
            for (int matricula : new int[] {1, 12, 23}) {
                tabela.inserir(matricula, "Aluno " + matricula, 20);
            }
            tabela.inserir(2, "Outra posicao", 21);
            verificar(tabela.remover(removida), "Falha ao remover " + removida);
            verificar(tabela.buscar(removida) == null, "Aluno removido ainda existe.");
            verificar(!tabela.remover(removida), "Segunda remocao deveria retornar false.");
            if (removida == 1) {
                verificarLista(tabela, 12, 23);
            } else if (removida == 12) {
                verificarLista(tabela, 1, 23);
            } else {
                verificarLista(tabela, 1, 12);
            }
            verificarLista(tabela, 2);
            verificar(!tabela.remover(45), "Remocao inexistente em lista ocupada falhou.");
            verificar(!tabela.remover(3), "Remocao em posicao vazia falhou.");
        }
        TabelaHash tabela = new TabelaHash();
        tabela.inserir(0, "Unico", 20);
        verificar(tabela.remover(0), "Falha ao remover o unico aluno.");
        verificar(tabela.buscar(0) == null, "Lista nao ficou vazia.");
        verificar(tabela.inserir(11, "Novo", 21), "Falha ao reutilizar posicao esvaziada.");
        verificarLista(tabela, 11);
        System.out.println("OK: remocoes no inicio, meio, fim, aluno unico e inexistente.");
    }

    private static String capturarExibicao(TabelaHash tabela) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream original = System.out;
        try (PrintStream captura = new PrintStream(buffer, true, StandardCharsets.UTF_8)) {
            System.setOut(captura);
            tabela.exibir();
        } finally {
            System.setOut(original);
        }
        return new String(buffer.toByteArray(), StandardCharsets.UTF_8);
    }

    private static String montarExibicao(TabelaHash tabela, boolean preenchida) {
        StringBuilder esperado = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            esperado.append("[").append(i).append("]: ");
            if (preenchida && i == 1) {
                esperado.append("[").append(tabela.buscar(1).getAluno()).append("] -> [")
                        .append(tabela.buscar(12).getAluno()).append("] -> [")
                        .append(tabela.buscar(23).getAluno()).append("]");
            } else if (preenchida && i == 10) {
                esperado.append("[").append(tabela.buscar(10).getAluno()).append("]");
            } else {
                esperado.append("Vazia");
            }
            esperado.append(System.lineSeparator());
        }
        return esperado.toString();
    }

    private static void testarExibicao() {
        TabelaHash tabela = new TabelaHash();
        verificar(capturarExibicao(tabela).equals(montarExibicao(tabela, false)),
                "Exibicao da tabela vazia incorreta.");
        for (int matricula : new int[] {1, 12, 23, 10}) {
            tabela.inserir(matricula, "Aluno " + matricula, 20);
        }
        verificar(capturarExibicao(tabela).equals(montarExibicao(tabela, true)),
                "Exibicao completa, dados ou setas incorretos.");
        verificarLista(tabela, 1, 12, 23);
        verificarLista(tabela, 10);
        for (int matricula : new int[] {1, 12, 23, 10}) {
            tabela.remover(matricula);
        }
        verificar(capturarExibicao(tabela).equals(montarExibicao(tabela, false)),
                "Exibicao apos remover todos os alunos incorreta.");
        System.out.println("OK: exibicao das 11 posicoes, dados, encadeamentos e posicoes vazias.");
    }
}
