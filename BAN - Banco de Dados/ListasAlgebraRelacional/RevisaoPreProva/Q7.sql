-- buscar os IDs e nomes dos intérpretes internacionais que estão presentes tanto em CDs de coletânea quanto em CDs de intérprete

-- 1° Separar CDS de interpretes e coletâneas
CDS_INTER =  sigma tipo = 'interprete' (cds)
CDS_COLET =  sigma tipo = 'coletanea' (cds)

-- 2° Achar interpretes nacionais
INTER_NACIONAIS = sigma nacionalidade = 'brasileiro' (interpretes)

-- 3° Achar interpretes que estão presentes em CDs de Interpretes (parte fácil)
INTER_NACIONAIS_INTER = pi interpretes.id, interpretes.nome (CDS_INTER join cds.interprete = interpretes.id INTER_NACIONAIS)

-- 4° Achar interpretes que estão presentes em CD de Coletâneas (parte difícil)
-- 4.1 Achar faixas dos CDs de coletâneas
FAIXAS = (faixas join faixas.cd = cds.id CDS_COLET)

-- 4.2 Achar interpretes nacionais que interpretaram as faixas do passo 4.1
INTER_NACIONAIS_COLET = pi interpretes.id, interpretes.nome (FAIXAS join faixas.interprete = interpretes.id INTER_NACIONAIS)

-- 5° Retornar ambos os resultados dos passos 3 e 4
INTER_NACIONAIS_INTER union INTER_NACIONAIS_COLET
