-- Buscar o nome e o salário dos funcionários de Florianópolis e Palhoça que estão internados como pacientes e têm consulta marcada em 20/10/2006.

-- 1° Achar funcionários de Floripa ou Palhoça
FUN = rho FUN_CIDADE (pi cpf (
    sigma cidade = 'Florianopolis' or cidade = 'Palhoca' (Funcionarios)
))

-- 2° Achar quais desses funcionários são pacientes
PAC = pi cpf (Pacientes)
FUN_PAC = sigma FUN_CIDADE.cpf = Funcionarios.cpf ((FUN ∩ PAC) x Funcionarios)

-- 3° Achar quais tem consultas pra essa data
pi nome, salario ((sigma data = date('2006-10-20') (Consultas)) left join FUN_PAC)

