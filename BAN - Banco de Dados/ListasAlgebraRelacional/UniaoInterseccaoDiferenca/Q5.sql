-- Buscar o Nome e CPF do pacientes que consultaram apenas (somente) Médicos de Florianópolis.

-- 1° Achar médicos fora de Floripa
MED_NF = sigma cidade != 'Florianopolis' (Medicos)

-- 2° Achar pacientes "errados" (que consultaram com gente de fora)
PAC_ERRADOS = pi codp (sigma Medicos.codm = Consultas.codm (MED_NF x Consultas))

-- 3° Achar resto dos pacientes 
PAC_COM_CONSULTA = pi codp (Consultas)

-- 4° Achar pacientes que valem pro enunciado
PACCERTOS = rho PAC_CERTOS (PAC_COM_CONSULTA - PAC_ERRADOS)

-- 5° Achar informações que o enunciado pede
pi nome, cpf (sigma PAC_CERTOS.codp = Pacientes.codp (PACCERTOS x Pacientes))

