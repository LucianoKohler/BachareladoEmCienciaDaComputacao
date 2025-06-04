from dados.espacoGeometrico import EspacoGeometrico
from dados.reta import Reta
from dados.ponto2D import Ponto2D
from dados.ObjetoSobrepostoException import  ObjetoSobrepostoException

esp = EspacoGeometrico()

# Adicionando retas
reta1 = Reta(1, 2)
try:
    esp.adicionarReta(reta1)
    print("Reta adicionada com sucesso")
except ObjetoSobrepostoException:
    pass

reta2 = Reta(1, 1)
try:
    esp.adicionarReta(reta2)
    print("Reta adicionada com sucesso")
except ObjetoSobrepostoException:
    pass

# Reta que intercepta
reta3 = Reta(2, -2)
try:
    esp.adicionarReta(reta3) # É para retornar erro
    print("Reta adicionada com sucesso")
except ObjetoSobrepostoException:
    pass

# Adicionando pontos
ponto1 = Ponto2D(2, 3)
try:
    esp.adicionarPonto(ponto1) # É para retornar erro
    print("Ponto adicionado com sucesso")
except ObjetoSobrepostoException:
    pass

ponto2 = Ponto2D(3, 2)
try:
    esp.adicionarPonto(ponto2)
    print("Ponto adicionado com sucesso")
except ObjetoSobrepostoException:
    pass