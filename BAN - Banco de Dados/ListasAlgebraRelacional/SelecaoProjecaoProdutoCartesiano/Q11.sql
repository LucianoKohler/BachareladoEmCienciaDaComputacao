-- Buscar, para as consultas marcadas para o período da manhã (7hs-12hs) do dia 25/09/06, o nome do médico, o nome do paciente e a data da consulta.

-- 1° Achar consultas no horário especificado (não é assim que se compara hora mas como ela é string não tem oq fazer)
CON = sigma data = date('2006-09-25') and hora >= '07:00:00' and hora <= '12:00:00' (Consultas)

-- 2° Achar médicos e pacientes para fazer o produto cartesiano
MED = pi codm, nome (Medicos)
PAC = pi codp, nome (Pacientes)


-- 3° Juntas tudo e pegar informações relevantes
pi Medicos.nome, Pacientes.nome, Consultas.data (
	sigma Medicos.codm = Consultas.codm and Pacientes.codp = Consultas.codp (
		(CON x MED) x PAC
	)
)

