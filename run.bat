@echo off
set COMANDO=%1
if "%COMANDO%"=="" set COMANDO=ordenacao

if /I "%COMANDO%"=="ordenacao" (
    if not exist bin mkdir bin
    javac -d bin ordenacao\ed2\projeto\algoritmosordenacao\*.java
    if errorlevel 1 exit /b 1
    java -cp bin ed2.projeto.algoritmosordenacao.Main
    goto :eof
)

if /I "%COMANDO%"=="testar" (
    if not exist bin mkdir bin
    javac -d bin ordenacao\ed2\projeto\algoritmosordenacao\*.java
    if errorlevel 1 exit /b 1
    java -cp bin ed2.projeto.algoritmosordenacao.Main --testar
    goto :eof
)

if /I "%COMANDO%"=="hash" (
    echo A Parte 2 ^(tabela-hash^) sera executada aqui quando implementada.
    goto :eof
)

if /I "%COMANDO%"=="clean" (
    if exist bin rmdir /s /q bin
    echo Pasta bin removida.
    goto :eof
)

echo Uso: run [ordenacao ^| testar ^| hash ^| clean]
