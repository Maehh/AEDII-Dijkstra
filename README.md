# AEDII-Dijkstra
## Trabalho de Estruturas de Dados II: Caminhos Mais Curtos

### Acesso ao projeto 📁
❗ **Este projeto usa JDK 22+**, certifique-se de que está propriamente instalado e configurado o path <br>
❗ _**Este é um projeto Maven V3.9+**_certifique-se de que está propriamente instalado e configurado com o path


### Instalar JDK 22 para windows
1. Baixe o JDK pelo [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) ou [Adoptium](https://adoptium.net/)
2. Instale o JDK 22 ou mais recente da sua escolha
3. Configure as Variaveis de ambiente <br>
   
```
set PATH=%JAVA_HOME%\bin;%PATH%
```

4. Verifique a instalação: <br>

```
java -version 
```

Você deve ver algo como: 
```
openjdk version "22.0.x"
```

### Instalar Maven para Windows

1. Acesse o site oficial [Apache Maven](https://maven.apache.org/download.cgi)
2. Baixe a versão Binary zip archive 
3. Extraia o arquivo .zip no diretório que desejar (por padrão, use C:\Arquivo_de_programas\Apache\Maven\ARQUIVO_DO_MAVEN como diretorio)
4. Configure as variáveis de ambiente no terminal: <br>

```
set MAVEN_HOME=C:\SEU_DIRETORIO\MAVEN 
set PATH=%MAVEN_HOME%\bin;%PATH% 
```
5. Certifique-se de que as variáveis estão configuradas <br>
   
```
mvn -v
```

Voce deve ver algo como: <br>

```
Apache Maven 3.9.x
Java version: 22, vendor: ...
``` 

#### Alterações no projeto para Eclipse IDE 🌒
1. Escolher Workspace
2. Selecionar arquivo > importar...
3. No menu de seleção: Maven > Projetos Maven Existentes
4. Inserir diretório do projeto
5. Finish/finalizar

#### Alterações no projeto para InteliJ IDE 🟦
1. Selecionar arquivo > abrir...
2. Selecionar **pasta** grafo-dijkstra
3. Escolher abrir como projeto **Maven**

#### Executando projeto ▶️
Basta Seguir o exemplo abaixo:

1. Acessar o terminal de sua preferência, 
2. Navegar até a pasta grafo-dijkstra e escrever:

```
mvn clean javafx:run
```

