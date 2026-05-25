-- Mostrar o nome dos funcionários que são pacientes, e que por sua vez tem consulta marcada com todos os médicos pediatras.

-- 1° Achar denominador (pediatras)
DEN = pi codm (sigma especialidade = 'pediatria' (Medicos))

-- 2° Achar numerador (funcionários pacientes com consulta marcada)
FUN_PAC = sigma Pacientes.cpf = Funcionarios.cpf (Pacientes x Funcionarios)
NUM = pi Pacientes.nome, Consultas.codm (
    sigma Consultas.codp = Pacientes.codp (Consultas x FUN_PAC)
)

-- 3° Dividir
NUM / DEN
