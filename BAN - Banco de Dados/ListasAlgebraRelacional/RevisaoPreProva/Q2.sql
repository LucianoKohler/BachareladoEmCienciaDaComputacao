-- buscar os nomes dos grupos brasileiros que produziram CDs em 2013
-- Nesse caso tanto discos de interpretes quanto trilhas sonoras/coletaneas podem produzir esses CDs, 
-- MAS COMO DÁ MUITO TRABALHO ACHAR OS DE TRILHA/COLETÂNEA, vou fazer só os de interpretes :D

-- 1° Cds de 2013
CDS = (sigma ano = 2013 (cds))
BRASILEIROS = sigma nacionalidade = 'brasileiro' (interpretes)

-- 2° Brasileiros que fizeram CDs
pi nome (sigma cds.interprete = interpretes.id (CDS x BRASILEIROS))

-- 3° Brasileiros que fizeram trilha sonora/coletâneas (não fiz mas aaacho que precisa também)
