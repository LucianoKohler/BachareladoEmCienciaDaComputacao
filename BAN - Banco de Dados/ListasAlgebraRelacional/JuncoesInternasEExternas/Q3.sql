-- Buscar o número e o andar dos ambulatórios onde nenhum médico dá atendimento.

-- 1° Encontrar ambulatórios em que médicos dão atendimento
AMB = rho AMB (pi nroa, andar (Ambulatorios join Medicos))

-- 2° A diferença é os que não tem médico atendendo
pi nroa, andar (pi nroa, andar (Ambulatorios) - AMB)

