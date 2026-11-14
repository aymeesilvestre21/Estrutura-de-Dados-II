# Documentação da Fase 3 — Simulações e Comparação

Registro das Etapas 9 e 10 da Parte 1 (Algoritmos de Ordenação). Esta fase separa estimativas teóricas de medições locais: são resultados diferentes e não devem ser comparados como se tivessem sido obtidos pelo mesmo método.

## 1. IA e metodologia

- **IA utilizada na análise:** assistente de IA do GitHub Copilot no VS Code, via Copilot SDK. O identificador exato do modelo não é exposto nesta sessão e, por isso, não é atribuído ou inventado.
- **Algoritmos fornecidos para análise:** as implementações Java de `BubbleSort`, `InsertionSort`, `SelectionSort`, `MergeSort` e `QuickSort` presentes no projeto.
- **Tamanhos:** adotando `n = 10`, foram considerados `n = 10`, `n² = 100`, `n³ = 1.000` e `n¹⁰ = 10.000.000.000`.
- **Método das estimativas:** análise das operações dominantes das implementações, assumindo uma taxa hipotética uniforme de `100.000.000` operações equivalentes por segundo. Essa taxa é apenas uma referência matemática; não foi medida no computador.
- **Modelos usados:** Bubble Sort usa o limite superior de `n(n-1)` comparações, pois a implementação percorre `n-1` posições em cada passagem sem reduzir o limite do laço; Insertion Sort usa `n²/4` para uma entrada aleatória no caso médio; Selection Sort faz exatamente `n(n-1)/2` comparações; Merge Sort usa `n log₂(n)` como aproximação; Quick Sort usa `0,9609 n log₂(n)` como aproximação média de comparações para entrada aleatória.

As estimativas ignoram custos de alocação, movimentação de dados, JVM, sistema operacional e hardware. Elas servem para comparar tendências de crescimento, não para prever o tempo de uma execução específica.

## 2. Simulação teórica — tempos estimados

Os valores desta tabela foram calculados pelos modelos acima e **não são medições cronometradas**.

| Algoritmo | `n = 10` | `n² = 100` | `n³ = 1.000` | `n¹⁰ = 10.000.000.000` |
| :--- | ---: | ---: | ---: | ---: |
| Bubble Sort (limite superior) | 0,0009 ms | 0,099 ms | 9,99 ms | aproximadamente 31.688 anos |
| Insertion Sort (caso médio) | 0,00025 ms | 0,025 ms | 2,5 ms | aproximadamente 7.922 anos |
| Selection Sort | 0,00045 ms | 0,0495 ms | 4,995 ms | aproximadamente 15.844 anos |
| Merge Sort | 0,00033 ms | 0,00664 ms | 0,0997 ms | aproximadamente 55,4 min |
| Quick Sort (caso médio) | 0,00032 ms | 0,00638 ms | 0,0957 ms | aproximadamente 53,2 min |

Os valores foram arredondados e, em especial, os tempos submilissegundo não representam precisão real de execução. `n¹⁰` é uma extrapolação matemática, não uma execução: um vetor Java de 10 bilhões de inteiros exigiria cerca de 40 GB apenas para os dados e excederia o limite de tamanho de um único array. Portanto, essa entrada não é executável como um único vetor nas condições do projeto.

## 3. Medições locais — benchmark executado

O benchmark pode ser repetido com `.\run.bat simular` no Windows ou `make simular` no Linux/macOS. Ele usa a mesma entrada embaralhada e determinística para cada algoritmo de um mesmo tamanho, faz cinco lotes de aquecimento, coleta 21 amostras e apresenta a mediana do tempo por vetor. A clonagem é feita fora do intervalo cronometrado; o pequeno custo do laço do benchmark permanece incluído, sendo mais relevante para vetores minúsculos. Cada resultado é conferido contra `Arrays.sort`; qualquer divergência interrompe o benchmark com erro.

Ambiente desta execução: Windows, Eclipse Temurin OpenJDK 21.0.13, processador Intel Core i5-1235U. Os valores podem variar em outra execução, JVM ou máquina.

| Algoritmo | 10 elementos (ms/vetor) | 100 elementos (ms/vetor) | 1.000 elementos (ms/vetor) |
| :--- | ---: | ---: | ---: |
| Bubble Sort | 0,0002 | 0,0140 | 0,9682 |
| Insertion Sort | 0,0002 | 0,0016 | 0,0737 |
| Selection Sort | 0,0002 | 0,0111 | 1,1479 |
| Merge Sort | 0,0006 | 0,0017 | 0,0252 |
| Quick Sort | 0,0005 | 0,0012 | 0,0160 |

Estas são **medições**, não estimativas. Para 10 elementos, o tempo é tão pequeno que o custo de medição e a JVM influenciam significativamente o resultado. O vetor aleatório de 1.000 elementos também não representa todos os casos possíveis, especialmente o pior caso do Quick Sort.

## 4. Explicação comparativa

- Bubble Sort e Insertion Sort têm crescimento quadrático no caso médio. Na implementação deste projeto, Bubble Sort pode terminar em tempo linear se o vetor já estiver ordenado; Insertion Sort também tem melhor caso linear.
- Selection Sort continua quadrático mesmo em um vetor ordenado, pois procura o menor elemento em cada passagem.
- Merge Sort tem crescimento `O(n log n)` previsível, mas usa memória auxiliar proporcional à entrada.
- Quick Sort tem crescimento médio `O(n log n)` e, por isso, tende a escalar bem em entradas grandes. Seu pior caso é `O(n²)`, então uma medição com entrada aleatória não cobre todas as entradas possíveis.
- As medições locais seguem a tendência geral: os algoritmos quadráticos crescem mais rapidamente; Merge Sort e Quick Sort tiveram tempos menores para 1.000 elementos neste vetor e ambiente. Diferenças pequenas em entradas curtas não devem ser generalizadas.
- A estimativa de `n¹⁰` evidencia o custo proibitivo dos algoritmos quadráticos e a impossibilidade prática de alocar o vetor. Mesmo algoritmos `O(n log n)` não tornam viável essa entrada como array Java no ambiente esperado.

## 5. Estimativa versus medição

| Resultado | Como foi obtido | Interpretação |
| :--- | :--- | :--- |
| **Estimativas teóricas desta fase** | Modelos de complexidade e taxa hipotética declarados na seção 1 | Projeções da IA; não foram cronometradas. A coluna `n¹⁰` não foi executada. |
| **Medições locais desta fase** | Benchmark Java descrito na seção 3 | Execução cronometrada em entradas embaralhadas de até 1.000 elementos, neste ambiente específico. |
| **Medições da Etapa 8** | [fase_2.md](./fase_2.md), seção 2 | Tempos registrados nos seis arquivos de teste do projeto. São medições de outras entradas e não devem ser tratadas como estimativas nem como garantia para outras máquinas. |

