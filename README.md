
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
│   └── .gitkeep
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

## Parte 2 — Tabela Hash

Implementar uma Tabela Hash com encadeamento para armazenar alunos.

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

## Compilação

No terminal, a partir da raiz do projeto, execute:

```powershell
javac -encoding windows-1252 -d . ordenacao\ed2\projeto\algoritmosordenacao\*.java
```

O comando compila os arquivos Java existentes na pasta de ordenação.

A codificação Windows-1252 é utilizada para compatibilidade com os arquivos originais fornecidos pelo professor.

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
- [ ] Implementação do menu principal.
- [ ] Conclusão das funcionalidades de ordenação.
- [ ] Implementação da Tabela Hash.
- [ ] Testes finais e integração.

## Entrega

- Prazo: 16/11/2026, às 23h59.
- Apresentação: 17/11/2026.
- Equipe: mínimo de 5 e máximo de 7 integrantes.
- Entrega do projeto completo no Blackboard por um integrante da equipe, incluindo os nomes de todos os participantes.
