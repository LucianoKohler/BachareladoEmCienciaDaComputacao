-- Buscar o número e a capacidade dos ambulatórios do segundo andar e o nome dos médicos ortopedistas que atendem neles.

-- 1° Achar ortopedistas
MED = pi nroa, nome(sigma especialidade = 'ortopedia' (Medicos))

-- 2° Achar ambulatórios do 2° andar
AMB = pi nroa, capacidade (sigma andar = 2 (Ambulatorios))

-- 3° Achar ortopedistas que atuam nos ambulatórios do segundo andar
pi Ambulatorios.nroa, capacidade, nome (sigma Medicos.nroa = Ambulatorios.nroa (MED x AMB))

