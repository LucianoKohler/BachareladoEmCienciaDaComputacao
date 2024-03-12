# Dando Deploy Automático com o GitHub Pages
*Créditos: Grande [Professor Renan Ponick](https://www.youtube.com/@theinitcode/videos)*

**Github Pages** é um serviço da plataforma Github que possibilita a hospedagem de um site (sem backend) na internet gratuitamente, ele é uma ótima maneira de hospedar seus projetinhos web para mostrar aos outros, quer um exemplo? Veja um projetinho meu!
- https://lucianokohler.github.io/comercio-liberado/

Normalmente um site comum, com só tecnologias básicas como HTML, CSS e JS possuem um passo-a-passo bem mais simples, que você provavelmente consegue executar sem minha ajuda, apenas entrar em `seu repositório>settings>pages` e se virar lá, mas agora, se você usa uma tecnologia mais desenvolvida, como React.Js, é necessário seguir uns passos a mais... Dê uma olhada no tutorialzinho:

- A princípio, crie seu projeto (criei o meu em react, com o comando `npx create react-app nomedoprojeto`)

- Após isso, dentro da pasta raíz, crie a seguinte sequência de arquivos: `.github/workflows/build.yml`

- Dentro do build.yml, coloque esse código aqui: 

```yml 
name: deploy 
on:
  push:
    branches: 
      - main 
    ###IMPORTANTE: Isso diz que o código será rodado quando houverem pushes na main (preferivelmente coloque mais branches, nunca dê push na main)
jobs: #tarefas a fazer
  deploy:
    runs-on: ubuntu-20.04 #OS do servidor que o Pages vai hospedar
    steps: #indica as tecnologias a serem usadas
      - uses: actions/checkout@v2
      - uses: actions/setup-node@v1
        with: #indica qual versão do node usar
          node-version: '18.x' #18.0 pra cima
      - name: Build web-app
        run: |
          npm ci 
          npm run build 
        #faz a build do projeto
      - name: Deploy to gh-pages
        uses: peaceiris/actions-gh-pages@v3
        with:
          github_token: ${{ secrets.GITHUB_TOKEN }}
          publish_dir: ./build
```

- Com o código feito, acesse no seu repositório, e então `settings>actions>general` e, ao fim da página:
    - marque a checkbox `Allow GitHub Actions to create and approve pull requests`
    - Marque o radio (checkbox redonda) `Read and write permissions`
    
- Clique em save

- Agora, acesse no seu repositório o menu actions e veja se o deploy foi bem sucedido

- Agora, vá em `settings>pages` e:
    - Escolha para dar deploy via branch
    - Mude o branch para `gh-pages` (que o bot criou automaticamente com o build.yml)
    - Espere um menu no topo da página que leve você ao seu site, agora com deploys automáticos!!!
