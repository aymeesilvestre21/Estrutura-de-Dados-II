package ed2.projeto.tabelahash;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        TabelaHash tabela = new TabelaHash();

        try (Scanner entrada = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== Tabela Hash ===");
                System.out.println("1 - Inserir");
                System.out.println("2 - Buscar");
                System.out.println("3 - Remover");
                System.out.println("4 - Exibir");
                System.out.println("5 - Encerrar");

                Integer opcao = lerInteiro(entrada, "Opcao: ", 1, 5);
                if (opcao == null) {
                    break;
                }
                if (opcao == 5) {
                    System.out.println("Programa encerrado.");
                    return;
                }
                if (opcao == 4) {
                    tabela.exibir();
                    continue;
                }

                Integer matricula = lerInteiro(entrada, "Matricula: ", 0, Integer.MAX_VALUE);
                if (matricula == null) {
                    break;
                }

                switch (opcao) {
                    case 1:
                        if (tabela.buscar(matricula) != null) {
                            System.out.println("Matricula ja cadastrada.");
                            break;
                        }
                        String nome = lerNome(entrada);
                        if (nome == null) {
                            return;
                        }
                        Integer idade = lerInteiro(entrada, "Idade: ", 1, Integer.MAX_VALUE);
                        if (idade == null) {
                            return;
                        }
                        if (tabela.inserir(matricula, nome, idade)) {
                            System.out.println("Aluno inserido com sucesso.");
                        } else {
                            System.out.println("Matricula ja cadastrada.");
                        }
                        break;
                    case 2:
                        No encontrado = tabela.buscar(matricula);
                        if (encontrado == null) {
                            System.out.println("Aluno nao encontrado.");
                        } else {
                            System.out.println(encontrado.getAluno());
                        }
                        break;
                    case 3:
                        if (tabela.remover(matricula)) {
                            System.out.println("Aluno removido com sucesso.");
                        } else {
                            System.out.println("Aluno nao encontrado.");
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    // Le a linha completa para evitar problemas ao alternar numeros e nomes.
    private static Integer lerInteiro(Scanner entrada, String mensagem, int minimo, int maximo) {
        while (true) {
            System.out.print(mensagem);
            if (!entrada.hasNextLine()) {
                System.out.println("\nEntrada encerrada. Programa encerrado.");
                return null;
            }
            try {
                int valor = Integer.parseInt(entrada.nextLine().trim());
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
                System.out.println("Valor invalido. Informe um inteiro entre " + minimo + " e " + maximo + ".");
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Digite um numero inteiro.");
            }
        }
    }

    private static String lerNome(Scanner entrada) {
        while (true) {
            System.out.print("Nome: ");
            if (!entrada.hasNextLine()) {
                System.out.println("\nEntrada encerrada. Programa encerrado.");
                return null;
            }
            String nome = entrada.nextLine().trim();
            if (!nome.isEmpty()) {
                return nome;
            }
            System.out.println("Nome invalido. Digite um nome nao vazio.");
        }
    }
}
