# Olá professor, pelo meu conhecimento em Python ser meio primitivo, manti a classe
# mais simples, apenas com um construtor e o método __str__

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

# Fim da classe

# Criando e mostrando as classes
harryPotter = Filme("Harry Potter e a Pedra Filosofal", 110, "Fantasia")
frozen = Filme("Frozen - Uma Aventura Congelante", 90, "Animação")

print("FILMES NO CATÁLOGO:\n")
print(harryPotter)
print(frozen)
