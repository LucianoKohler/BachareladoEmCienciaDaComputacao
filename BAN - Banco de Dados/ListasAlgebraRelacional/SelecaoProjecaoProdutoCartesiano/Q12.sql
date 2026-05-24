-- Buscar o nome e o salário dos funcionários de Florianópolis e Palhoça que estão internados como pacientes e têm consulta marcada com psiquiatras.

-- 1° Achar consultas marcadas com psiquiatras
CON = sigma Medicos.especialidade = 'psiquatra' (
    (sigma Consultas.codm = Medicos.codm (Consultas x Medicos))
)

-- 2° Achar Funcionários de Palhoça/Florianópolis
FUN = sigma cidade = 'Palhoca' or cidade = 'Florianopolis' (Funcionarios)

-- 3° Achar os CPFs dos pacientes que têm consultas com psiquiatras
PAC_PSI = sigma Consultas.codp = Pacientes.codp (CON x Pacientes)

-- 4° Achar funcionários que são pacientes do conjunto acima
pi Funcionarios.nome, Funcionarios.salario (
    sigma Pacientes.cpf = Funcionarios.cpf (PAC_PSI x FUN)
)

