import time
import matplotlib.pyplot as plt

def buscaSeq(arr, alvo, n):
    for i in range(0, n):
        if arr[i] == alvo:
            return i
    
    return -1


def buscaBin(arr, alvo, n):
  esq = 0
  dir = n - 1

  while esq <= dir:
    meio = (esq + dir) // 2 # // = Div. inteira

    if arr[meio] == alvo:
      return meio
    elif arr[meio] < alvo:
      esq = meio + 1
    else:
      dir = meio - 1

  return -1


# CÓDIGO PRINCIPAL
amostras = []
tempoSeq = []
tempoBin = []

print("Fazendo buscas, espere aí!");
arr = list(range(0, 100_000_001)) # Cria lista inteira de uma vez, e só corta na hora da busca

for i in range(10_000_000, 100_000_001, 10_000_000):
    mediaBuscaSeq = 0;
    mediaBuscaBin = 0;

    alvo = i # Pior caso, elemento que não existe no vetor
    
    inicioBuscaSeq = time.perf_counter();
    buscaSeq(arr, alvo, i);
    fimBuscaSeq = time.perf_counter();

    inicioBuscaBin = time.perf_counter();
    buscaBin(arr, alvo, i);
    fimBuscaBin = time.perf_counter();

    amostras.append(i)
    tempoBin.append(fimBuscaBin - inicioBuscaBin)
    tempoSeq.append(fimBuscaSeq - inicioBuscaSeq)

    print(f"Busca com i = {i:,} feita")

# Printando valores no terminal
print("|-------------|-----------|-----------|")
print("|   AMOSTRA   | Tempo Seq.| Tempo Bin.|")

for i in range(10):
    print(f"| {amostras[i]:11,} | {tempoSeq[i]:.7f}s| {tempoBin[i]:.7f}s|")

print("|-------------|-----------|-----------|")

# Plotagem
plt.figure()

# gráfico 1
plt.subplot(1, 2, 1)
plt.plot(amostras, tempoSeq)
plt.xlabel("Tamanho da amostra")
plt.ylabel("Tempo (segundos)")
plt.title("Busca Sequencial")

# gráfico 2
plt.subplot(1, 2, 2)
plt.plot(amostras, tempoBin)
plt.xlabel("Tamanho da amostra")
plt.ylabel("Tempo (segundos)")
plt.title("Busca Binária")

plt.tight_layout()
plt.show()