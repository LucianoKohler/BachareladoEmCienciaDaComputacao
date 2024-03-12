# Empurrando Um Repositório Local Para o GitHub
*Créditos: Grande [Professor Renan Ponick](https://www.youtube.com/@theinitcode/videos)*

Se você tem um repositório local mas não sabe como enviá-lo para a nuvem do *Github*, veja como fazer isso de uma maneira fácil e rápida:

- Com o arquivo pronto (com as informações que você quer), abra o terminal (exemplo feito com o VSCode) com `ctrl + "` ou `ctrl + shift + "` para criar um novo terminal, **Certifique-se que o caminho do terminal termina na pasta dos seus arquivos**;

- Escreva no terminal `git init` para inicializar um repositório na sua máquina;

- Digite `git add .` para salvar arquivos novos e/ou alterações no repositório local;

- Em seguida, dê o clássico *commit* com o comando `git commit -m "Descrição do commit"` para salvar essa versão do código no repositório local;

- Por fim, no menu "Source Control" no VSC, clique no botão para enviar o repositório local para o remoto;

- Em seguida, fique no troca troca de requisições de login entre o VSC e o GitHub, e por fim, escolha se o repositório será privado ou público;

- Para enviar futuras mudanças no projeto, utilize o comando `git push`.
