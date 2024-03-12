# Registering a Device's SSH key 
*Credits: Great [Teacher Renan Ponick](https://www.youtube.com/@theinitcode/videos)*

The Git technology allows you to commit on your local repositories to save your documents, although, to send those changes to Github, it is usually needed to confirm your identity so that Github doesn't suspect on you, to do that, follow these instructions of how to register a **SSH key** (Secure Shell):

- Open your *Git Bash* (if you don't have it, istall it in its official website [here](https://git-scm.com/downloads)) and verify if there is already a SSH key there with the command `ls -al ~/.ssh`;

- If there isn't any, create one with `ssh-keygen -t ed25519 -C "email@example.com"` (The weird code is the default for Github);

- Start an agent (lil' SSH robot) with the command `eval "$(ssh-agent -s)"`;

- Give the SSH key to the lil' robot with the line `ssh-add ~/.ssh/id_ed25519`;

- Now, use the clip command to copy your SSH key to your clipboard: `clip < ~/.ssh/id_ed25519.pub`;

- With the key in hand, open your Github and go to `settings>SSH and GPG keys`;

- Click on the add SSH key button, give a name to this key (e.g. Home, Work...) to identify it. After that, paste your key on the big textfield;

- At last, command `ssh -T git@github.com` on your bash to connect everything and type yes to confirm your connection, that's it!
