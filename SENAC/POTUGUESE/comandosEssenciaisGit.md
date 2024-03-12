# Comandos Essenciais do Git
*Créditos: Grande [Professor Renan Ponick](https://www.youtube.com/@theinitcode/videos)*

```bash
- git status
# Indica em que branch você está
# Os arquivos modificados/adicionados/deletados
# Se alguma coisa precisa ser adicionada ao repositório

- git add <filename ou . >
# Adiciona arquivos novos da pasta para o repositório;
 
- git restore --staged <filename ou . >
# Usado para reverter o git add

- git branch < branchname >
# Cria um branch novo

- git checkout < branchname >
# Entra no branch escolhido

- git checkout -b < branchname >
# Cria um novo branch e entra nele

- git commit -m < description >
# Cria uma versão do código, como um "save em um jogo" no repositório local, junto com uma descrição

- git push
# Envia as novas alterações/commits do repositório local para o repositório remoto

- git branch -D < branchname >
# Deleta um branch escolhido

- git fetch
# Puxa as novas alterações/commits enviados ao repositório remoto para seu repositório local MAS não altera seu código

- git pull
# Puxa as novas alterações/commits enviados ao repositório remoto para seu repositório local E altera seu código para a versão mais recente puxada pelo comando
```
