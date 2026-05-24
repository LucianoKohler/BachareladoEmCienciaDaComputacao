-- Buscar pares de nomes dos médicos diferentes que têm consultas marcadas nas mesmas datas. Cuidar para não repetir um mesmo par na resposta.

-- 1° Pegar informações dos médicos com consultas
MED = rho MED (pi Medicos.codm, nome, data (sigma Consultas.codm = Medicos.codm (Consultas x Medicos)))

-- 2° Duplicar esses dados
MED2 = rho MED2 (MED)

-- 3° Achar pares, (resultado = 1 e 2, 1 e 3), usar != possibilita duplicatas, por isso usar sempre "<"
sigma MED.nome < MED2.nome and MED.data = MED2.data (MED x MED2)
