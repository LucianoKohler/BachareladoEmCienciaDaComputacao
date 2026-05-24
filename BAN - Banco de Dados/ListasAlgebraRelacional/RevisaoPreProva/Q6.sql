-- buscar pares de IDs e nomes de funcionários que trabalham nos mesmos turnos, sem repetir um mesmo par na resposta (em posições diferentes).

-- Relação com ela mesma -> rho
-- 1° Clone de funcionário para gerar pares funcionário x funcionário
FUN = rho funClone (pi id, nome, turno (funcionarios))

-- Busca por funcionários de mesmo turno, o "<" serve pra não ter pares repetidos
sigma funClone.turno = funcionarios.turno and funClone.id < funcionarios.id (funcionarios x FUN)

