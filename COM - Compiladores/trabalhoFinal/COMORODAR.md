O interpretador já está compilado por meio das tecnologias:
- **alex** para o analisador léxico
- **happy** para o Parser sintático

Mas se você quiser recompilá-los, basta rodar no terminal, nessa sequêcia:
- `alex Lexer.x`
- `happy --ghc Parser.y`
    - *A flag `--ghc` é para caso você compile o código no ghc*

Após isso, só rodar o Semantico.hs (ex. com `ghci Semantico.hs`) no seu compilador **e chamar a função testSemantico** (só escrever `testSemantico` no interlúdio), ela então retornará o código anotado e com seus devidos logs retornados.

*Note que a saída do código em si dos analisadores é bem abstrata para um ser humano entendê-la, então ao final dos arquivos **Parser.y** e **Lexer.x**, há uma versão formatada do output de cada analisador para melhor entender o que cada um está retornando*