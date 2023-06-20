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
# Cria uma versão do código, como um "save em um jogo" no repositório local, junto com uma descrição;

- git push
# Envia as novas alterações/commits do repositório local para o repositório local;

- git branch -D < branchname >
# Deleta um branch escolhido;

- git fetch
# Puxa as novas alterações/commits enviados ao repositório remoto para seu repositório local mas não altera seu código;

- git pull
# Puxa as novas alterações/commits enviados ao repositório remoto para seu repositório local E altera seu código para a versão mais recente puxada pelo comando;
```

# Cadastrando a chave SSH de um dispositivo no GitHub

- Abra seu *Git Bash* e verifique se já há uma chave SSH com o comando ```ls -al ~/.ssh```;
- Se não houver uma, crie com o comando ```ssh-keygen -t ed25519 -C "email@example.com"```;
- Inicialize um agente (robozinho de SSH) com o comando ```eval "$(ssh-agent -s)"```
- 
- Com a chave em mãos, logue sua conta no GitHub e abra as *settings* > SSH and GPG keys
- Clique no botão de adicionar uma chave SSH, dê um nome a essa chave (ex: casa, trabalho...) para identificação, em seguida, cole o código previamente copiado na caixa de texto grande. 

# Empurrando um repositório local para o GitHub

- Com o arquivo pronto (com as informações que você quer), abra o terminal com ctrl + " (ou ctrl + shift + " para criar um novo terminal), **Certifique-se que o caminho do terminal termina na pasta dos seus arquivos**;

- Escreva no terminal ```git init``` para inicializar um repositório **Público** na sua máquina;

- Digite ```git add .``` para salvar arquivos novos e/ou alterações no repositório local;

- Em seguida, dê o clássico *commit* com o comando ```git commit -m "Descrição do commit"``` para salvar essa versão do código no repositório local;

- Por fim, no menu "Source Control" no VSC, clique no botão para enviar o repositório local para o remoto;

- Em seguida, fique no troca troca de requisições de login entre o VSC e o GitHub, e por fim, escolha se o repositório será privado ou público;

- Para enviar futuras mudanças no projeto, utilize o comando ```git push```.