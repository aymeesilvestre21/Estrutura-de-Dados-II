.PHONY: all ordenacao testar hash clean

all: ordenacao

ordenacao:
	@javac -d bin ordenacao/ed2/projeto/algoritmosordenacao/*.java
	@java -cp bin ed2.projeto.algoritmosordenacao.Main

teste:
	@javac -d bin ordenacao/ed2/projeto/algoritmosordenacao/*.java
	@java -cp bin ed2.projeto.algoritmosordenacao.Main --testar

hash:
	@echo "Compilando e executando Parte 2 (Tabela Hash)..."
	@if ls tabela-hash/*.java >/dev/null 2>&1 || find tabela-hash -name "*.java" | grep -q .; then \
		javac -d bin $$(find tabela-hash -name "*.java"); \
		echo "Classes da Tabela Hash compiladas em bin/."; \
	else \
		echo "A Parte 2 (tabela-hash) ainda nao possui arquivos .java implementados."; \
	fi

clean:
	@rm -rf bin
	@echo "Pasta bin/ removida."
