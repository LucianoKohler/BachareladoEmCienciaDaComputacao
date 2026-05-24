-- Bucar o código dos pacientes que têm consultas marcadas com todos os médicos.

-- 1° Quais pacientes tem consultas (e os médicos associados para dividir)
PAC_CON = pi codp, codm (Consultas)

-- 2° Listar médicos
MED = pi codm (Medicos)

-- 3° Listar quem tem consulta com todos eles (divisão remove o campo que foi comparado, no caso codm, sobrando só codp e nome)
PAC_CON / MED

