# Documentação da Fase 2 — Algoritmos de Ordenação

Documentação técnica e didática referente à implementação das **Etapas 4 a 8** da Parte 1 (Algoritmos de Ordenação) do projeto de **Estrutura de Dados II**.

---

## 1. Visão Geral e Arquitetura

O módulo de ordenação foi estruturado no pacote `ed2.projeto.algoritmosordenacao` seguindo o padrão de projeto comportamental **Strategy**:

```text
ordenacao/
├── dados/
│   ├── entrada_pequena.txt      (20 números aleatórios)
│   ├── entrada_grande.txt       (500 números aleatórios)
│   ├── numeros_repetidos.txt    (30 números com duplicatas)
│   ├── numeros_negativos.txt    (25 números negativos, zero e positivos)
│   ├── vetor_ordenado.txt       (30 números em ordem crescente)
│   └── vetor_inverso.txt        (30 números em ordem decrescente)
└── ed2/
    └── projeto/
        └── algoritmosordenacao/
            ├── IAlgoritmoOrdenacao.java  (Interface Strategy)
            ├── BubbleSort.java           (Estratégia 1)
            ├── InsertionSort.java        (Estratégia 2)
            ├── SelectionSort.java        (Estratégia 3)
            ├── MergeSort.java            (Estratégia 4)
            ├── QuickSort.java            (Estratégia 5)
            ├── Utils.java                (Leitura de .txt, clonagem, exibição e validação)
            └── Main.java                 (Menu interativo e bateria de testes)
```

### Decisões de Arquitetura
- **Padrão Strategy (`IAlgoritmoOrdenacao`)**: A interface `IAlgoritmoOrdenacao` define o contrato único `void ordenarVetor(int[] vetor)`. Cada algoritmo implementa essa interface de forma independente, permitindo que a classe `Main` execute qualquer um dos 5 algoritmos polimorficamente e sem acoplamento.
- **Preservação dos Dados de Entrada**: Como os algoritmos ordenam o vetor *in-place* (alterando o próprio array em memória), a classe `Utils` realiza a clonagem do vetor original antes de cada execução, garantindo comparações justas sobre a mesma entrada.
- **Isolamento de Binários (`bin/`)**: Todos os arquivos compilados (`.class`) são direcionados para a pasta `bin/` na raiz do projeto, mantendo os diretórios de código-fonte limpos.

---

## 2. O Que Foi Implementado por Etapa

### Etapa 4: Revisão e Conclusão dos Algoritmos de Ordenação
- **Padronização para `UTF-8`**: Os arquivos `QuickSort.java` e `Utils.java` foram convertidos da codificação `Windows-1252` para `UTF-8`, garantindo compilação limpa em Windows, Linux e macOS.
- **Validação de segurança**: Adicionada verificação `if (vetor == null || vetor.length <= 1) return;` no início do método `ordenarVetor` dos 5 algoritmos.
- **Otimização de memória no `MergeSort`**: Ajustado o método `intercala` para alocar o vetor auxiliar `aux` apenas com o tamanho do subvetor atual (`fim - inicio + 1`) em vez do tamanho total `vetor.length` a cada chamada recursiva, assegurando complexidade $O(n \log n)$ em entradas grandes.

### Etapa 5: Menu Interativo de Ordenação (`Main.java`)
- Criada a classe `Main.java` com menu direto no console:
  - Opções `1` a `5`: Executa individualmente `BubbleSort`, `InsertionSort`, `SelectionSort`, `MergeSort` ou `QuickSort`.
  - Opção `6`: Executa e compara os 5 algoritmos em sequência sobre os mesmos dados carregados.
  - Opção `7`: Permite alternar entre os 6 arquivos `.txt` de teste ou informar um caminho de arquivo personalizado.
  - Opção `0`: Encerra o programa.

### Etapa 6: Entrada de Dados via Arquivo `.txt` (`Utils.java`)
- Implementado o método `Utils.lerVetorDeArquivo(String caminhoArquivo)`, capaz de ler inteiros (positivos, negativos e zero) separados por espaços, quebras de linha, vírgulas ou ponto-e-vírgula.
- Implementado `Utils.clonarVetor(int[] vetorOriginal)` usando `Arrays.copyOf`, garantindo que cada algoritmo receba uma cópia intacta do vetor desordenado original (já que a ordenação é feita *in-place*).

### Etapa 7: Exibição dos Resultados e Limite de 100 Elementos (`Utils.java`)
- Atualizado o método `Utils.imprimirVetor(int[] vetor)` com a constante `LIMITE_EXIBICAO = 100`.
- Quando o vetor possui mais de 100 números (como em `entrada_grande.txt` com 500 itens), o sistema imprime apenas os 100 primeiros elementos seguidos do aviso `...} (exibindo os 100 primeiros de 500 elementos)`.
- Implementado `Utils.estaOrdenado(int[] vetor)` para verificar automaticamente se `vetor[i] <= vetor[i + 1]` após a ordenação.

### Etapa 8: Bateria de Testes Práticos
Os 5 algoritmos foram testados nos 6 cenários abaixo e ordenaram corretamente todas as entradas:

| Arquivo de Teste | Itens | Bubble Sort | Insertion Sort | Selection Sort | Merge Sort | Quick Sort |
| :--- | :---: | :---: | :---: | :---: | :---: | :---: |
| `entrada_pequena.txt` | 20 | 0.0185 ms | **0.0065 ms** | 0.0075 ms | 0.0319 ms | 0.0206 ms |
| `entrada_grande.txt` | 500 | 2.7316 ms | 1.5424 ms | 1.5905 ms | 0.4341 ms | **0.2429 ms** |
| `numeros_repetidos.txt` | 30 | 0.0071 ms | 0.0134 ms | **0.0042 ms** | 0.0116 ms | 0.0204 ms |
| `numeros_negativos.txt` | 25 | 0.0030 ms | 0.0068 ms | **0.0017 ms** | 0.0035 ms | 0.0033 ms |
| `vetor_ordenado.txt` | 30 | **0.0010 ms** | 0.0022 ms | 0.0201 ms | 0.0033 ms | 0.0038 ms |
| `vetor_inverso.txt` | 30 | 0.0550 ms | 0.0130 ms | **0.0026 ms** | 0.0041 ms | 0.0034 ms |

---

## 3. Como Funciona Cada Algoritmo (Explicação Didática)

Para exemplificar cada algoritmo de forma simples, considere o vetor inicial: **`[5, 2, 4, 1, 3]`**.

### 3.1. Bubble Sort (Ordenação da Bolha)
- **Ideia central**: Uma "lupa" percorre o vetor olhando apenas **dois vizinhos por vez** (`vetor[i]` e `vetor[i + 1]`). Se o número da esquerda for maior que o da direita, eles trocam de lugar. A cada volta completa, o maior número "borbulha" até o final da fila.
- **Passo a passo com `[5, 2, 4, 1, 3]`**:
  - **1ª Volta**: O `5` compara com `2` (troca), com `4` (troca), com `1` (troca) e com `3` (troca) $\rightarrow$ `[2, 4, 1, 3, 5]`.
  - **2ª Volta**:
    - A lupa olha `[2, 4]`: como `2 < 4`, não troca e a lupa avança para o `4`.
    - A lupa olha `[4, 1]`: troca! Fica `[2, 1, 4, 3, 5]`.
    - A lupa olha `[4, 3]`: troca! Fica `[2, 1, 3, 4, 5]`.
  - **3ª Volta**: A lupa começa no início e vê `[2, 1]` lado a lado $\rightarrow$ troca! Fica `[1, 2, 3, 4, 5]`.
  - **4ª Volta**: Passa conferindo sem fazer nenhuma troca (`isTroca = false`) e encerra.

### 3.2. Insertion Sort (Ordenação por Inserção)
- **Ideia central**: Funciona como organizar **cartas de baralho na mão**. A parte esquerda já está em ordem; pegamos a próxima carta da direita, empurramos as cartas maiores uma posição para a direita e encaixamos a carta no espaço correto.
- **Passo a passo com `[5, 2, 4, 1, 3]`**:
  1. `[5]` já está ordenado. Pega o `2`, empurra o `5` e insere o `2` $\rightarrow$ `[2, 5, 4, 1, 3]`.
  2. Pega o `4`, empurra apenas o `5` e insere o `4` $\rightarrow$ `[2, 4, 5, 1, 3]`.
  3. Pega o `1`, empurra `2, 4, 5` e insere o `1` no início $\rightarrow$ `[1, 2, 4, 5, 3]`.
  4. Pega o `3`, empurra `4, 5` e insere o `3` $\rightarrow$ `[1, 2, 3, 4, 5]`.

### 3.3. Selection Sort (Ordenação por Seleção)
- **Ideia central**: Varre a lista inteira procurando **o menor número de todos** e o coloca direto na 1ª posição (fazendo apenas uma troca por rodada). Depois repete procurando o menor entre os restantes para a 2ª posição, e assim sucessivamente.
- **Passo a passo com `[5, 2, 4, 1, 3]`**:
  1. Menor de todos é `1` $\rightarrow$ troca com o `5` da posição 0: `[1, 2, 4, 5, 3]`.
  2. Menor do restante `[2, 4, 5, 3]` já é o `2` (mantém).
  3. Menor de `[4, 5, 3]` é `3` $\rightarrow$ troca com `4`: `[1, 2, 3, 5, 4]`.
  4. Menor de `[5, 4]` é `4` $\rightarrow$ troca com `5`: `[1, 2, 3, 4, 5]`.

### 3.4. Merge Sort (Ordenação por Intercalação — Dividir para Conquistar)
- **Ideia central**: Divide o vetor ao meio recursivamente até restarem pedaços de 1 elemento. Depois, vai juntando (*intercalando*) os pedaços de dois em dois já em ordem crescente com o auxílio de um vetor temporário (`aux`).
- **Passo a passo com `[5, 2, 4, 1]`**:
  1. **Divisão**: `[5, 2, 4, 1]` $\rightarrow$ `[5, 2]` e `[4, 1]` $\rightarrow$ `[5]`, `[2]`, `[4]`, `[1]`.
  2. **1ª Intercalação**: Junta `[5]` com `[2]` $\rightarrow$ `[2, 5]`; junta `[4]` com `[1]` $\rightarrow$ `[1, 4]`.
  3. **Intercalação Final**: Compara o início de `[2, 5]` com `[1, 4]` e monta `[1, 2, 4, 5]`.

### 3.5. Quick Sort (Ordenação Rápida — Estratégia do Pivô)
- **Ideia central**: Escolhe um elemento central como **Pivô** e reorganiza o vetor (particionamento) de modo que todos os elementos menores fiquem à esquerda do pivô e todos os maiores fiquem à direita. Em seguida, repete o mesmo processo para o lado esquerdo e para o lado direito.
- **Passo a passo com `[5, 2, 3, 1, 4]`**:
  1. Escolhe o elemento central como pivô (`3`).
  2. Joga os menores para a esquerda e os maiores para a direita: `[2, 1]` | `3` | `[5, 4]`.
  3. Ordena recursivamente a esquerda (`[1, 2]`) e a direita (`[4, 5]`), resultando em `[1, 2, 3, 4, 5]`.

---

## 4. Como Compilar e Executar

### No Linux e macOS (via `Makefile`)
O comando abaixo compila todos os arquivos `.java` dentro da pasta isolada `bin/` e inicia o menu interativo de ordenação:
```bash
make ordenacao
```

O comando abaixo compila o projeto e executa automaticamente a bateria de testes práticos da Etapa 8 nos 6 arquivos `.txt`:
```bash
make testar
```

### No Windows — PowerShell ou CMD (via `run.bat`)
O comando abaixo executa o script `run.bat` no Windows para compilar as classes na pasta `bin` e abrir o menu interativo:
```powershell
.\run ordenacao
```

O comando abaixo executa a bateria completa de testes da Etapa 8 no Windows:
```powershell
.\run testar
```
