-- Buscar o nome dos médicos que têm consulta marcada e as datas das suas consultas
pi Medicos.nome, Consultas.data (sigma Consultas.codm = Medicos.codm (Consultas x Medicos))