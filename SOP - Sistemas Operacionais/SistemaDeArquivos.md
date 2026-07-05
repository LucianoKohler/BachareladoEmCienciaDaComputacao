# Introdução

Dados podem ser necessários além do ciclo de vida de um processo, então é necessário guardá-los em algum lugar para que possam ser alterados/lidos em um momento futuro (a chamada **memória secundária**)

A gestão dos arquivos é feita pelo **sistema de arquivos**, que executa inúmeras coisas:
- Nomeia arquivos
- Lida e organiza a estrutura dos arquivos
- Indica os tipos de arquivos suportados por uma operação
- Lida com os atributos associados à arquivos
- Gerencia o espaço em disco
- etc...

# Arquivos

Não precisam de nomes, mas ajudam à organizar a interface do usuário, além disso, possuem extensões que podem servir para o *SO*, ou só serem convenientes para o usuário

## Estrutura de um arquivo

- **Sequência de Bytes (comumente usada)**: O *SO*, não entende o arquivo, e ele só faz sentido para as aplicações, o arquivo é "separado" byte a byte
- **Registros de tamanho fixo**: Ao invés do arquivo ser separadinho, ele é separado em blocos (tipo declaração de tamanho no SQL), inflexível, mas mais organizado.
- **Árvore**: Cada registro tem um ponteiro para o próximo índice do arquivo

## Acesso

Pode ser
- **Sequencial**: Quero acessar o byte 1000, tenho que começar do 0 (bom para carregar vídeos, áudios, etc..., pois o leitor não precisa ficar pulando pelo arquivo) 

- **Direto**: Mais comum, acesso à qualquer parte do arquivo, também conhecido como acesso aleatório (**random access**)

# Partições

Um computador pode ter várias "partições" (pedaços de memória separados), assim que é possível ter um dual-boot, por exemplo.

Ao ligar o computador, ele segue uma ordem fixa:
1. **Vai para o MBR** para inicializar corretamente e carregar a partição ativa do PC (qual SO usar)
2. **Vai para a Tabela de partições** para encontrar informações sobre o tamanho da partição, qual é a ativa, etc...
3. **Cada partição tem um**
    - Bloco de Boot, que carrega o SO no computador
    - Superbloco, que guarda parâmetros e regras do SO
    - Informações de gerenciamento, que fala sobre os espaços livres na memória e etc...
    - Os dados propriamente ditos

    