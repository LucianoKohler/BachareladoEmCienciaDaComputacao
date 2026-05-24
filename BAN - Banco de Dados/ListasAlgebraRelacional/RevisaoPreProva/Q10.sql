-- um novo funcionário foi admitido na loja. Seu código é 25 e ele vai trabalhar no mesmo turno do
-- funcionário João da Silva (CPF = 22334455667) e receber o mesmo salário dele. O nome do novo
-- funcionário é Pedro Souza, seu CPF é 11223344556, seu RG é 9999999999 e ele reside na rua X, 333,
-- Joinville.

-- Novamente pedi pra IA, descobri que RelaX não suporta modificações e inserções, por isso que dava erro.

-- 1° Isolar os dados do funcionário modelo (João da Silva) através do CPF fornecido
JOAO = sigma cpf = 22334455667 (funcionarios)

-- 2° Criar o registro do Pedro Souza capturando dinamicamente o salário e o turno do João
-- Projetamos os dados fixos do Pedro e "puxamos" as colunas de salário e turno vindas da tabela JOAO
NOVO_FUNC = pi 25->id, 'Pedro Souza'->nome, 11223344556->cpf, 9999999999->fone, '9999999999'->rg, 'rua X, 333, Joinville'->endereco, JOAO.salario->salario, JOAO.turno->turno (JOAO)

-- 3° A INSERÇÃO REAL NO BANCO DE DADOS
-- Unimos a tabela original com a nova tupla e salvamos o resultado de volta na relação de funcionários
funcionarios <- (funcionarios union NOVO_FUNC)