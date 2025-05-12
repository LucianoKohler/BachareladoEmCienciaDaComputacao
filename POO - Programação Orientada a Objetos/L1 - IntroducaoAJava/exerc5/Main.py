class Filme:
    def __init__(self, nome, duracao, genero):
        self.nome = nome
        self.duracao = duracao
        self.genero = genero

    def __str__(self):
        str = ""
        str += f"Nome: {self.nome}\n"
        str += f"Duração: {self.duracao} minutos\n"
        str += f"Gênero: {self.genero}\n"
        return str

# Fim classe Filme

class Conta:
    def __init__(self, userName, senha, premium):
        self.userName = userName
        self.senha = senha
        self.premium = premium

    def __str__(self):
        str = ""
        str += f"Nome de usuário: {self.userName}\n"
        str += f"Senha: {self.senha}\n"
        str += f"É premium? {"Sim" if self.premium else "Não"}\n"
        return str

# Fim classe Conta

# Criando e mostrando as classes
harryPotter = Filme("Harry Potter e a Pedra Filosofal", 110, "Fantasia")
frozen = Filme("Frozen - Uma Aventura Congelante", 90, "Animação")

# Criando contas
c1 = Conta("Zezin123", "123@@@aa", False)
c2 = Conta("ContaPremium", "premium123", True)

print("FILMES NO CATÁLOGO:\n")
print(harryPotter)
print(frozen)

print("CONTAS CRIADAS:\n")
print(c1)
print(c2)

