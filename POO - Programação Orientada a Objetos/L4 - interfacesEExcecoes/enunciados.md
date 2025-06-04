# Questão 1:
Implemente quatro operações (Soma, Multiplicação, Mod e MDC) **sem usar os métodos pré-existentes em Java** (como usar mod para retornar o módulo), além de implementá-los com uma interface que possui o método *executar*, que recebe dois valores e efetua a operação da classe, após isso mostre que funciona na *main* com valores aleatórios

# Questão 2:
Implemente um sistema de gestão de processos, onde haja uma função **distribuirProcessos()**, que distribui aleatoriamente todos os processos pelos juízes cadastrados no sistema, nota-se que cada juíz tem um **máximo de 5 processos simultâneos**, então crie *exceptions* para tratar com erros como o de enviar um processo para um juíz ocupado, ou o de existirem processos sem dono se não houverem juízes para tratá-los, implemente uma *main* com exemplos para mostrar o funcionamento

# Questão 3:
Implemente um **sistema de gerenciamento de arquivos**, onde é possível criar um *documento*, *música* ou *vídeo* (todos derivados da classe pai *arquivo*), e inseri-lo em um diretório fictício, é necessário lançar a exception **NomeInvalidoException** se o nome conter um dos seguintes itens:
- Possuir quebras de linha (\n)
- Possuir colchetes ou chaves ([], {})
- Possuir aspas simples ou duplas (', ")
- Possuir menos de 10 ou mais de 256 caracteres

Utilize um *HashMap* para implementar o sistema de diretórios, e crie uma *main* para mostrar o funcionamento do programa

# Questão 4:
Crie em Python, um sistema gerenciador de linhas e pontos no espaço geométrico bidimensional, onde é possível inserir *retas* e *pontos*, uma reta tem os métodos **Intercepta(Reta r)**, que diz se a reta **r** intercepta ela, e o método **pertenceAReta(Ponto p)**, que retorna se o ponto **p** pertence a reta. Se for o caso de qualquer uma das duas (reta interceptante ou ponto pertencente), é necessário lançar a exception ObjetoSobrepostoException, implemente uma *main* para mostrar o funcionamento do programa