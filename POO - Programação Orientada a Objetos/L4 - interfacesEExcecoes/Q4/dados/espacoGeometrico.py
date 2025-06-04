from dados.ObjetoSobrepostoException import ObjetoSobrepostoException

class EspacoGeometrico:
    def __init__(self):
        self.retas = {}
        self.pontos = {}

    def adicionarReta(self, novaReta):
        for reta in self.retas.values():
            if reta.intercepta(novaReta):
                raise ObjetoSobrepostoException(reta)
        self.retas[len(self.retas)] = novaReta

    def adicionarPonto(self, novoPonto):
        for reta in self.retas.values():
            if reta.estaNaReta(novoPonto):
                raise ObjetoSobrepostoException(novoPonto)
        self.pontos[len(self.pontos)] = novoPonto
