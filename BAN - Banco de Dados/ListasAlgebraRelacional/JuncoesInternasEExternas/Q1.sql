-- Buscar o número e a capacidade dos ambulatórios do quinto andar e o nome dos médicos que atendem neles.

-- Left join pois queremos todos os ambulatórios, mesmo aqueles sem médicos
AMBS = sigma andar = 5 (Ambulatorios ⟕ Medicos)
pi nroa, capacidade, nome (AMBS)

