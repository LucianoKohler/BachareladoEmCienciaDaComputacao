from dados.pessoa import Pessoa

class Aluno(Pessoa):
    def __init__(self, nome, n1, n2, n3, n4, n5):
        super().__init__(nome)
        self.notas = [n1, n2, n3, n4, n5]

    def calcularMedia(self):
        return sum(self.notas) / 5

    def __repr__(self):
        return f"Aluno: {self.nome}, Média: {self.calcularMedia():.1f}, Passou de ano? {"Sim" if self.calcularMedia() >= 7 else "Não"}"