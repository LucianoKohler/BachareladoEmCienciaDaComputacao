# Incializando Git Local
*Créditos: Grande [Professor Renan Ponick](https://www.youtube.com/@theinitcode/videos)*

Para criar um repositório local, é necessário seguir uma sequência de passos no seu bash para que tudo fique nos trinques, veja como criar seu repositório logo abaixo:

- Abra o bash, e insira a linha de código `git --version`  para ver se o git está na versão correta; 

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
