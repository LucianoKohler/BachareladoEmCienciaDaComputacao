-- Buscar os dados de todos os médicos e, para aqueles que têm consultas marcadas, mostrar os dados de suas consultas.
Medicos left join Consultas

-- Só 1 linha bizarro eu sei, mas o left join garante que todos os registros de médicos sejam 
-- mantidos, então os que não tem consultas vão ter null nos campos de consulta