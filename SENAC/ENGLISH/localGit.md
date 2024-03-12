# Initializing Local Git
*Credits: Great [Teacher Renan Ponick](https://www.youtube.com/@theinitcode/videos)*

To create a local rep, it is necessary to follow a list of steps on your bash so that everything works fine, take a look on how to create your rep below:

- Open your bash and use the command `git --version` to see if your git is OK;

- Now, configure your user with the commands:
    ```bash
    git config --global user.name "Your name"
    git config --global user.email "Your email"
    git config --global core.editor "Your IDE" (optional)
    ```

- To create the rep locally, use `mkdir folder_name` to, you know, create your folder;

- Enter the new folder with `cd folder_name`;

- Use the magical `git init` to initialize the repository;

- After all, use `code .` to open your folder on your preferred code editor.
