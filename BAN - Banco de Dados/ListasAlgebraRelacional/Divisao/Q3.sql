-- Buscar o nome e o CPF dos pacientes que têm consultas marcadas com todos os médicos ortopedistas que atendem nos ambulatórios do primeiro andar.

-- 1° Achar denominador (ortopedistas que atendem no 1° andar)
AMB = sigma andar = 1 (Ambulatorios)
MED = sigma especialidade = 'ortopedia' (Medicos)
DEN = pi Medicos.codm (AMB join MED)

-- 2° Achar numerador (Pacientes com consulta)
-- Notei que se der pi (codm, nome, cpf) dá erro, ordem importa!! (O ultimo elemento é o que será comparado na divisão)
NUM = pi nome, cpf, codm (Consultas join Pacientes)

-- 3° Dividir
NUM / DEN