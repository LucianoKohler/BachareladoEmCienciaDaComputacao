# Comandos Essenciais do Git
*Credits: Great [Teacher Renan Ponick](https://www.youtube.com/@theinitcode/videos)*

```bash
- git status
# Tells you in which branch you are
# The modifyed/added/removed files
# If something needs to be added to the repository

- git add <filename ou . >
# Adds new files to the rep
 
- git restore --staged <filename ou . >
# Used to revert git add

- git branch < branchname >
# Creates a new branch

- git checkout < branchname >
# Enters the referred branch

- git checkout -b < branchname >
# Creates a new branch and enters it

- git commit -m < description >
# Creates a new version of the code, like a "save or checkpoint in a game" on the local rep, along with a description

- git push
# Sends the new changes/commits from the local rep to the remote rep

- git branch -D < branchname >
# Deletes the chosen branch

- git fetch
# Pulls the new changes/commits sent from the remote rep to your local rep BUT it won't change your current code

- git pull
# Pulls the new changes/commits sent from the remote rep to your local rep but it WILL change your current code
```
