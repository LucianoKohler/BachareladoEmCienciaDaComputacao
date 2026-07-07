### Considerações

O interpretador já está compilado por meio das tecnologias:
- **alex** para o analisador léxico
- **happy** para o Parser sintático
- **jasmin** para o Montador de bytecodes

Mas se você quiser recompilá-los, basta rodar no terminal, nessa sequêcia:
- `alex Lexer.x`
- `happy --ghc Parser.y`
    - *A flag `--ghc` é para caso você compile o código no ghc*

Após isso, só rodar o Main.hs (ex. com `ghci Main.hs`) no seu compilador **e chamar a função compila** (só escrever `compila` no interlúdio), ela então criará o arquivo `output.j` e com seus devidos logs retornados.

### Resumo
- `alex /Lexer.x`
- `happy --ghc analisadores/Parser.y`
- `ghci -ianalisadores Main.hs` e então `compila`
    - `-ianalisadores` indica que os includes estão em `./analisadores`
- `java -jar jasmin.jar Output.j`
- `java Output`
- **Note que o arquivo de input (.j--) precisa ser passado por código no Main.hs**

**COMPRIMIDO**
- `alex analisadores/Lexer.x && happy --ghc analisadores/Parser.y && ghc -ianalisadores Main.hs -e compila && java -jar jasmin.jar Output.j && java Output`

*Note que a saída do código em si dos analisadores é bem abstrata para um ser humano entendê-la, então ao final dos arquivos **Parser.y** e **Lexer.x**, há uma versão formatada do output de cada analisador para melhor entender o que cada um está retornando*