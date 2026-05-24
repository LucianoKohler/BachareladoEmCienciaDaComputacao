-- Buscar o nome, CPF e idade dos médicos, pacientes e funcionários que residem em Florianópolis.

MED = pi nome, cpf, idade (sigma cidade = 'Florianopolis' (Medicos))
PAC = pi nome, cpf, idade (sigma cidade = 'Florianopolis' (Pacientes))
FUN = pi nome, cpf, idade (sigma cidade = 'Florianopolis' (Funcionarios))

(MED ∪ PAC) ∪ FUN

