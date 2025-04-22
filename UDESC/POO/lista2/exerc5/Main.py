import json

# Definindo a classe filme
class Filme:
    def __init__(self, titulo, ano, classificacao, nota):
        self.titulo = titulo
        self.ano = ano
        self.classificacao = classificacao
        self.nota = nota

    @classmethod
    def from_JSON(cls, JSON):
        # Se JSON for uma string, transforma em JSON, se já for JSON, só deixa como string
        dados = json.loads(JSON) if isinstance(JSON, str) else JSON
        return cls(dados["titulo"], dados["ano"], dados["classificacao"], dados["nota"])

    def __str__(self):
        s = ""
        s += f"Título: {self.titulo}\n"
        s += f"Ano de lançamento: {self.ano}\n"
        s += f"Classificação: {self.classificacao}\n"
        s+= f"Nota: {self.nota}\n"
        return s

# Abrindo nosso arquivo JSON
with open('dados.json', 'r') as file:
    dados = json.load(file)

# Transformando nosso JSON em uma lista de objetos Filme
filmes = []

for filme in dados:
    f = Filme.from_JSON(filme)
    filmes.append(f)

# Ordenando os filmes por nota:
for i in range(0, len(filmes)):
    for j in range(0, len(filmes)):
        if filmes[i].nota > filmes[j].nota:
            temp = filmes[i]
            filmes[i] = filmes[j]
            filmes[j] = temp

# Mostrando os filmes ordenados:
print("\nFILMES NO CATÁLOGO ORDENADOS PELA NOTA:")
for filme in filmes:
    print(filme)