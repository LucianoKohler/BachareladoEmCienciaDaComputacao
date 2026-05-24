-- Buscar o nome e o CPF dos médicos que têm consultas marcadas com todos os pacientes.

-- 1° Listar todos os médicos com consultas (e os codp pra divisão)
MED = pi codm, codp (Consultas)

-- 2° Listar todos os pacientes
PAC = pi codp (Pacientes)

-- 3° Divisão (retorna só codm)
MED_TODOS = MED / PAC

-- 4° Pegar dados relevantes
pi Medicos.nome, Medicos.cpf (MED_TODOS join Medicos)

