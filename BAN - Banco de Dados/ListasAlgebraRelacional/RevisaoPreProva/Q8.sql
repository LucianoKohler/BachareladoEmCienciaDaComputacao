-- buscar os IDs e nomes dos funcionários do noturno que venderam exemplares de todos os CDs interpretados pela cantora Ivete Sangalo;

-- 1° ACHANDO EXEMPLARES DA IVETE SANGALO
-- 1.1 Achar todos os CDs da Ivete Sangalo
ID_IVETE_SANGALO = pi id (sigma nome = 'Ivete Sangalo' (interpretes))
CDS_IVETE_SANGALO = (cds join cds.interprete = interpretes.id ID_IVETE_SANGALO)

-- 1.2 Achar exemplares que contém os CDs da Ivete Sangalo
EXEMPLARES_IVETE_SANGALO = pi exemplares.cd, exemplares.exemplar (exemplares join exemplares.cd = cds.id CDS_IVETE_SANGALO)

-- 2° ACHAR FUNCIONÁRIOS NOTURNOS QUE VENDERAM EXEMPLARES
FUNCIONARIOS = pi funcionarios.id, funcionarios.nome, vendas.cd, vendas.exemplar (vendas join vendas.funcionario = funcionarios.id (sigma turno = 'N' funcionarios))

-- 3° ACHAR FUNCIONÁRIOS QUE VENDERAM TODOS OS EXEMPLARES DA IVETE SANGALO
FUNCIONARIOS / EXEMPLARES_IVETE_SANGALO

-- NOTE que como o enunciado pediu especificamente da Ivete Sangalo (que não existe no 
-- BD, temos que EXEMPLARES_IVETE_SANGALO) é vazio, logo a divisão não remove funcionário algum
-- pois tecnicamente todos os funcionários venderam todos os CDs da Ivete se ela tem 0 CDs na loja... 