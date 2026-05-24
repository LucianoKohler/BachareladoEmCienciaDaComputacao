-- Buscar o nome e cpf dos funcionários que recebem salários abaixo de R$500 e não estão internados como pacientes.

-- 1° Achar funcionários que não são pacientes
FUN = rho FUN (pi cpf (Funcionarios) - pi cpf (Pacientes))

-- 2° Achar funcionários que ganham menos de 500
pi Funcionarios.nome, Funcionarios.cpf (
    sigma FUN.cpf = Funcionarios.cpf and Funcionarios.salario < 500 (FUN x Funcionarios)
)

