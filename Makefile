.PHONY: all ordenacao testar visualizar simular hash clean

all: ordenacao

ordenacao:
	@javac -d bin ordenacao/ed2/projeto/algoritmosordenacao/*.java
	@java -cp bin ed2.projeto.algoritmosordenacao.Main

teste:
	@javac -d bin ordenacao/ed2/projeto/algoritmosordenacao/*.java
	@java -cp bin ed2.projeto.algoritmosordenacao.Main --testar

visualizar:
	@javac -d bin ordenacao/ed2/projeto/algoritmosordenacao/*.java
	@java -cp bin ed2.projeto.algoritmosordenacao.VisualizadorOrdenacao

simular:
	@javac -d bin ordenacao/ed2/projeto/algoritmosordenacao/*.java
	@java -cp bin ed2.projeto.algoritmosordenacao.BenchmarkOrdenacao

hash:
	@mkdir -p bin
	@javac -d bin $$(find tabela-hash -name "*.java")
	@java -cp bin ed2.projeto.tabelahash.Principal

clean:
	@rm -rf bin
	@echo "Pasta bin/ removida."
