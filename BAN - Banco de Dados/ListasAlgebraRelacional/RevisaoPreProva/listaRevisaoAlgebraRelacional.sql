--  buscar os IDs e títulos dos CDs de coletânea e trilha sonora. Deseja-se apenas CDs com duração superior a 90 e inferior a 120 min

pi id, titulo (sigma tipo != 'interprete' and duracao > 90 and duracao < 120 (cds))

------------------------------------------------------------------------------

-- buscar os nomes dos grupos brasileiros que produziram CDs em 2013
-- Nesse caso tanto discos de interpretes quanto trilhas sonoras/coletaneas podem produzir esses CDs

-- 1° Cds de 2013
CDS = (sigma ano = 2013 (cds))
BRASILEIROS = sigma nacionalidade = 'brasileiro' (interpretes)

-- 2° Brasileiros que fizeram CDs DE INTERPRETES
pi nome (sigma cds.interprete = interpretes.id (CDS x BRASILEIROS))

-- 3° Brasileiros que fizeram trilha sonora/coletâneas
-- Muito trabalho...

------------------------------------------------------------------------------

-- buscar o IDs e títulos do CDs com preço superior a R$ 30,00. Se o CD possuir um intérprete, apresentar o nome e o tipo do intérprete; 
pi cds.id, cds.titulo, interpretes.nome, interpretes.tipo (sigma preco > 30 (cds join cds.interprete = interpretes.id interpretes))

------------------------------------------------------------------------------

-- buscar os códigos de barra dos CDs vendidos em 30/04/2011 e 01/05/2014 (exceto venda com cartão) e os nomes e IDs dos funcionários que realizaram as vendas; (nota do luciano: prof quis dizer exemplar eu acho...)

-- 1° Achar vendas que suprem o enunciado
VEND = sigma (data = date('2011-04-30') or data = date('2014-05-01')) and formapgto != 'cartao' (vendas)

-- Juntar vendas com os funcionários que venderam elas
VEND_FUNC = (VEND join vendas.funcionario = funcionarios.id funcionarios)

-- Juntar vendas com os exemplares vendidos
VEND_COMPLETA = (VEND_FUNC join (VEND_FUNC.cd = exemplares.cd and VEND_FUNC.exemplar = exemplares.exemplar) exemplares)

-- Retirar dados relevantes
pi codBarras, funcionarios.nome, funcionarios.id (VEND_COMPLETA)

------------------------------------------------------------------------------

-- buscar as identificações (CD+exemplar) dos exemplares com status ok que estão disponíveis para venda; 

-- 1° Achar exemplares disponíveis para venda
VENDIDOS = rho VENDIDO (pi vendas.cd, vendas.exemplar, exemplares.status (sigma vendas.cd = exemplares.cd and vendas.exemplar = exemplares.exemplar (vendas x exemplares)))

-- 2° Achar exemplares não vendidos (todos - vendidos)
N_VENDIDOS = (pi cd, exemplar, status (exemplares) - VENDIDOS)

-- 3° Desses exemplares, encontrar os OKs
OKS = sigma status = 'ok' (N_VENDIDOS)

-- 4° Extrair as informações relevantes
pi cd, exemplar (OKS)

------------------------------------------------------------------------------

-- buscar pares de IDs e nomes de funcionários que trabalham nos mesmos turnos, sem repetir um mesmo par na resposta (em posições diferentes).

-- Relação com ela mesma -> rho
-- 1° Clone de funcionário para gerar pares funcionário x funcionário
FUN = rho funClone (pi id, nome, turno (funcionarios))

-- Busca por funcionários de mesmo turno, o "<" serve pra não ter pares repetidos
sigma funClone.turno = funcionarios.turno and funClone.id < funcionarios.id (funcionarios x FUN)

------------------------------------------------------------------------------
