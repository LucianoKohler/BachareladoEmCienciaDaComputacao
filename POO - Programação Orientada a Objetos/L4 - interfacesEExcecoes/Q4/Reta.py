class Reta():
    def __init__(self, a, b):
        self.a = a
        self.b = b

    def intercepta(self, reta):
        # Se o coeficiente angular for diferente, elas se interceptam
        return reta.a != self.a

    def estaNaReta(self, ponto):
        return ponto.y == self.a * ponto.x + self.b