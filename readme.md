# Incializando git local

  - Abra o bash, e insira a linha de código `git --version `  para ver se o git está na versão correta; 

- Agora configure seu usuário com os comandos:
    ```bash
    git config --global user.name "Seu nome"
    git config --global user.email "Seu email"
    git config --global core.editor "Nome da sua IDE" (opcional)
    ```
- Para criar o repositório localmente, use o `mkdir nome_da_pasta` para criar a pasta usada para inicializar o repositório;

- Entre na nova pasta com o comando `cd nome_da_pasta`;
- Use o comando `git init` para inicializar o repositório;
- Por fim, use a linha `code .` para abrir essa pasta pelo seu editor de código.

# Comandos essenciais do git
```bash
- git status
# Indica em que branch você está
# Os arquivos modificados/adicionados/deletados
# Se alguma coisa precisa ser adicionada ao projeto

- git add <filename ou . >
# Adiciona arquivos novos da pasta para o repositório;
 
- git restore --staged <filename ou . >
# Usado para reverter o git add;

- git branch < branchname >
# Cria um branch novo;

- git checkout < branchname >
# Entra no branch escolhido;

- git checkout -b < branchname >
# Cria um novo branch e entra nele;

- git commit -m < description >
# Cria uma versão do código, como um "save em um jogo" no repositório local;

- git push
# Envia as novas alterações/commits do repositório local para o repositório local;

- git branch -D < branchname >
# Deleta um branch escolhido;

- git fetch
# Puxa as novas alterações/commits enviados ao repositório remoto para seu repositório local mas não altera seu código;

- git pull
# Puxa as novas alterações/commits enviados ao repositório remoto para seu repositório local E altera seu código para a versão mais recente puxada pelo comando;
