-- Mostrar em uma relação o CPF e o nome de todos os pacientes e de todos os médicos, apresentando estes dados de forma relacionada (na mesma linha) para aqueles que possuem consultas marcadas.

-- Ao fazer o primeiro join (pacientes right join consultas), codm vira Consultas.codm, e na hora de fazer o 
-- join com Medicos, ele não encontra codm, e como Medicos não tem a coluna Consultas.codm, a junção falha, 
-- por isso usei sigma mesmo (dá de fazer com join mas tem que passar condições pra indicar qual campo será analisado nas tabelas)
pi Pacientes.nome, Pacientes.cpf, Medicos.nome, Medicos.cpf (
    sigma Consultas.codm = Medicos.codm (
        (Pacientes right join Consultas) x Medicos
    )
)

