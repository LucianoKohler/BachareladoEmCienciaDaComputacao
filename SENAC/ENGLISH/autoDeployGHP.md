# Autodeploying with Github Pages
*Credits: Great [Teacher Renan Ponick](https://www.youtube.com/@theinitcode/videos)*

**Github Pages** is a Github service that lets you host your website (no backends!) on the internet for free! It is a great way to host your web reps and show them to your friends, wanna see an example?, Take a look at one of my projects!
- https://lucianokohler.github.io/comercio-liberado/

Usually for a simple website with only basic techs such as HTML, CSS and JS, it is much easier to host your code, just go to `your repository>settings>pages` and you should be able to follow the tab's instructions on how to host it, buuut, if you're using some heavy weapons, such as React.Js, you'll need to follow some extra steps, take a look at this tutorial:

- At first, I created my project (in react with the command `npx create react-app projectname`);

- After that, inside the root folder, create the following file sequence: `.github/workflows/build.yml`;

- In the build.yml, throw this code there:

```yml 
name: deploy 
on:
  push:
    branches: 
      - main 
    ###IMPORTANT: This tells the code that it will be runned when there are pushes on the main branch (please, place more branches here and never push on main)
jobs:
  deploy:
    runs-on: ubuntu-20.04 #server OS
    steps: #Used techs
      - uses: actions/checkout@v2
      - uses: actions/setup-node@v1
        with: #Indicates the Node version
          node-version: '18.x' #18.0 and newer
      - name: Build web-app
        run: |
          npm ci 
          npm run build 
        #Builds the project
      - name: Deploy to gh-pages
        uses: peaceiris/actions-gh-pages@v3
        with:
          github_token: ${{ secrets.GITHUB_TOKEN }}
          publish_dir: ./build
```

- With the code OK, open your rep on Github and access `settings:actions:general`, on the end of the tab:
  - Check the `Allow GitHub Actions to create and approve pull requests`;
  - Check the `Read and write permissions`;

- Click on save;

- Now, access your the actions tab on your rep and see if the deploy was successful;

- To wrap things up, go to `settings>pages` and:
  - Choose to deploy from a branch;
  - Change the branch to `gh-pages` (the one the bot created with the build.yml);
  - Wait for a menu on the top of the tab that will take you to your website, now automatically deployed!!!
