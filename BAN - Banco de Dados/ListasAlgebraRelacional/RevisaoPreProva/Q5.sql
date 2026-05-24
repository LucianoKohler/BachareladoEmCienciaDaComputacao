-- buscar as identificações (CD+exemplar) dos exemplares com status ok que estão disponíveis para venda; 

-- 1° Achar exemplares disponíveis para venda
VENDIDOS = rho VENDIDO (pi vendas.cd, vendas.exemplar, exemplares.status (sigma vendas.cd = exemplares.cd and vendas.exemplar = exemplares.exemplar (vendas x exemplares)))

-- 2° Achar exemplares não vendidos (todos - vendidos)
N_VENDIDOS = (pi cd, exemplar, status (exemplares) - VENDIDOS)

-- 3° Desses exemplares, encontrar os OKs
OKS = sigma status = 'ok' (N_VENDIDOS)

-- 4° Extrair as informações relevantes
pi cd, exemplar (OKS)