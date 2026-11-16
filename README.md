
# Estrutura de Dados II

Projeto acadêmico da disciplina de Estrutura de Dados II, desenvolvido em equipe, com implementação de algoritmos de ordenação e Tabela Hash com encadeamento em Java.

## Objetivo

Aplicar conceitos de estruturas de dados e algoritmos por meio do desenvolvimento de duas partes:

- Parte 1: Algoritmos de Ordenação (1,0 ponto).
- Parte 2: Tabela Hash com Encadeamento (1,5 ponto).

## Estrutura do projeto

```text
Estrutura-de-Dados-II/
├── ordenacao/
│   └── ed2/
│       └── projeto/
│           └── algoritmosordenacao/
│               ├── BubbleSort.java
│               ├── InsertionSort.java
│               ├── MergeSort.java
│               ├── QuickSort.java
│               ├── SelectionSort.java
│               ├── Utils.java
│               └── IAlgoritmoOrdenacao.java
├── tabela-hash/
│   └── ed2/
│       └── projeto/
│           └── tabelahash/
│               ├── Aluno.java
│               ├── ListaEncadeada.java
│               ├── No.java
│               ├── Principal.java
│               ├── TabelaHash.java
│               └── TesteTabelaHash.java
├── .gitignore
└── README.md
```

## Parte 1 — Algoritmos de Ordenação

O projeto contempla os seguintes algoritmos:

- Bubble Sort
- Insertion Sort
- Selection Sort
- Merge Sort
- Quick Sort

### Requisitos

- Completar as implementações fornecidas pelo professor.
- Criar uma classe principal com menu para selecionar o algoritmo.
- Permitir a leitura de números de um arquivo TXT.
- Exibir os dados de entrada e saída, limitando a exibição aos primeiros 100 elementos quando necessário.
- Realizar simulações com diferentes tamanhos de entrada utilizando IA.
- Apresentar uma tabela comparativa das simulações, informando a IA utilizada.

As simulações e a comparação das Etapas 9 e 10 estão documentadas em [docs/fase_3.md](./docs/fase_3.md). Os tempos estimados estão identificados separadamente das medições práticas da Etapa 8.

## Parte 2 — Tabela Hash

A Tabela Hash com encadeamento armazena alunos e oferece inserção, busca, remoção e exibição pelo menu.

### Requisitos

- Tabela com 11 posições.
- Função Hash: h(chave) = chave mod 11.
- Armazenar matrícula, nome e idade.
- Impedir matrículas duplicadas.
- Inserir colisões no final da lista encadeada.
- Criar menu para inserir, buscar, remover, exibir e encerrar.
- Não utilizar bibliotecas prontas de implementação de Tabela Hash.

## Ambiente de desenvolvimento

- Linguagem: Java
- JDK: 21
- IDE: Visual Studio Code
- Controle de versão: Git e GitHub

## Compilação e Execução

O projeto conta com atalhos prontos para **Linux, macOS e Windows**, compilando automaticamente os arquivos `.class` na pasta isolada `bin/` e iniciando o menu:

### Linux e macOS (via `make`)

```bash
make ordenacao
```

### Windows — PowerShell ou CMD (via `run.bat`)

```powershell
.\run ordenacao
```

### Demonstração visual no terminal

Para acompanhar o Bubble Sort organizando um vetor pequeno com barras ASCII:

```powershell
.\run.bat visualizar
```

No Linux e macOS, use `make visualizar`.

### Benchmark dos algoritmos

Para medir os cinco algoritmos em vetores embaralhados de 10, 100 e 1.000 elementos:

```powershell
.\run.bat simular
```

No Linux e macOS, use `make simular`. Os resultados dependem da máquina e da JVM.

### Tabela Hash

Para compilar e iniciar o menu da Tabela Hash:

```powershell
.\run.bat hash
```

No Linux e macOS, use `make hash`.

### Comandos manuais do Java (qualquer sistema)

Para compilar manualmente na pasta isolada `bin/`:

```bash
javac -d bin ordenacao/ed2/projeto/algoritmosordenacao/*.java
```

Para executar o menu principal a partir da pasta `bin/`:

```bash
java -cp bin ed2.projeto.algoritmosordenacao.Main
```

Para executar o menu da Tabela Hash a partir da pasta `bin/`:

```bash
java -cp bin ed2.projeto.tabelahash.Principal
```

## Organização do desenvolvimento

- `main`: branch principal.
- `develop`: branch de integração.
- Branches de funcionalidade: utilizadas para desenvolvimento e revisão antes da integração.

## Status

- [x] Configuração inicial do ambiente Java.
- [x] Criação e organização das pastas.
- [x] Importação das classes de ordenação.
- [x] Criação da interface IAlgoritmoOrdenacao.
- [x] Verificação da compilação inicial.
- [x] Implementação do menu principal.
- [x] Conclusão das funcionalidades de ordenação.
- [x] Implementação da Tabela Hash.
- [x] Testes finais e integração.

## Entrega

- Prazo: 16/11/2026, às 23h59.
- Apresentação: 17/11/2026.
- Equipe: mínimo de 5 e máximo de 7 integrantes.
- Entrega do projeto completo no Blackboard por um integrante da equipe, incluindo os nomes de todos os participantes.
