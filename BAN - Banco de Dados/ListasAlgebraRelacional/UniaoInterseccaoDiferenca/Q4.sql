-- Buscar o nome e cpf dos médicos pediatras que não atendem nos ambulatórios 101 e 102, e estão internados como pacientes sofrendo de gastrite.

-- 1° Achando médicos que não estão no 101 nem 102 e são pediatras
MED = pi cpf (sigma nroa != 101 and nroa != 102 and especialidade = 'pediatria' (Medicos))

-- 2° Dos médicos acima, achando os que estão internados
MEDINT = rho MED_INT (MED ∩ pi cpf (Pacientes))

-- 3° Achando informões relevantes deles
pi Medicos.nome, Medicos.cpf (
    sigma MED_INT.cpf = Medicos.cpf (MEDINT x Medicos)
)


