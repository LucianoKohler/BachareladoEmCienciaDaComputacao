# Cadastrando a Chave SSH de Um Dispositivo
*Créditos: Grande [Professor Renan Ponick](https://www.youtube.com/@theinitcode/videos)*

A tecnologia Git permite que você dê commits nos seus repositórios locais para fazer o salvamento do seu documento, porém, para enviar essas alterações ao Github, normalmente é necessário validar sua identidade para o Github não desconfiar de você, para isso, siga esse passo-a-passo de como cadastrar uma **chave SSH** (Secure Shell):

- Abra seu *Git Bash* (se não tiver, instale-o no site oficial [aqui](https://git-scm.com/downloads)) e verifique se já há uma chave SSH com o comando `ls -al ~/.ssh`;

- Se não houver uma, crie com o comando `ssh-keygen -t ed25519 -C "email@exemplo.com"` (O código bizarro é padrão do GitHub);

- Inicialize um agente (robozinho de SSH) com o comando `eval "$(ssh-agent -s)"`;

- Dê a chave SSH ao robozinho com a linha de código `ssh-add ~/.ssh/id_ed25519`;

- Agora, utilize o comando clip para copiar para a área de transferência, sua chave SSH: `clip < ~/.ssh/id_ed25519.pub`;

- Com a chave em mãos, logue sua conta no GitHub e abra as `settings>SSH and GPG keys`;

- Clique no botão de adicionar uma chave SSH, dê um nome a essa chave (ex: casa, trabalho...) para identificação. Em seguida, cole o código previamente copiado na caixa de texto grande;

- Por fim, use `ssh -T git@github.com` no bash para conectar tudo e digite yes para confirmar a conexão, está feito!
