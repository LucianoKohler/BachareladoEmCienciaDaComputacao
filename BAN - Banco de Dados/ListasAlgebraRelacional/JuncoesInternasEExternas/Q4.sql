-- Buscar o número dos ambulatórios que estão no mesmo andar do ambulatório que tem nroa=1 e possuem capacidade superior à capacidade dele.
AMB_ALVO = rho AMB_ALVO (sigma nroa = 1 (Ambulatorios))

pi Ambulatorios.nroa (
    sigma AMB_ALVO.andar = Ambulatorios.andar and Ambulatorios.capacidade > AMB_ALVO.capacidade (AMB_ALVO x Ambulatorios)
)
