-- buscar os códigos de barra dos CDs vendidos em 30/04/2011 e 01/05/2014 (exceto venda com cartão) e os nomes e IDs dos funcionários que realizaram as vendas; 

-- nota do luciano: prof quis dizer exemplar, afinal CD não tem código de barras...

-- 1° Achar vendas que suprem o enunciado
VEND = sigma (data = date('2011-04-30') or data = date('2014-05-01')) and formapgto != 'cartao' (vendas)

-- Juntar vendas com os funcionários que venderam elas
VEND_FUNC = (VEND join vendas.funcionario = funcionarios.id funcionarios)

-- Juntar vendas com os exemplares vendidos
VEND_COMPLETA = (VEND_FUNC join (VEND_FUNC.cd = exemplares.cd and VEND_FUNC.exemplar = exemplares.exemplar) exemplares)

-- Retirar dados relevantes
pi codBarras, funcionarios.nome, funcionarios.id (VEND_COMPLETA)
