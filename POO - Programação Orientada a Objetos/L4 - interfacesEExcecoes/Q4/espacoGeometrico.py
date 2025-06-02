from ObjetoSobrepostoException import ObjetoSobrepostoException

class espacoGeometrico():
    retas = {}
    pontos = {}
    def adicionarReta(self, novaReta):
        for reta in self.retas:
            if reta.intercepta(novaReta):
                raise ObjetoSobrepostoException(reta)
        self.retas[len(self.retas)] = novaReta

    def adicionarPonto(self, novoPonto):
        for reta in self.retas:
            if reta.estaNaReta(novoPonto):
                raise ObjetoSobrepostoException(novoPonto)