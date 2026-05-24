-- Buscar o nome e o cpf dos funcionários que recebem salários iguais ou superiores ao salário do funcionário com cpf 51000110000.
FUNC_ALVO = rho FUNC_ALVO (sigma cpf = 51000110000 (Funcionarios))

pi Funcionarios.nome, Funcionarios.cpf (
    sigma Funcionarios.salario >= FUNC_ALVO.salario (Funcionarios x FUNC_ALVO)
)

