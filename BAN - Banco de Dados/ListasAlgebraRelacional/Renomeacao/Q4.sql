-- Mostrar o nome e idade do(s) médico(s) mais novo(s), ou seja, que possui a menor idade cadastrada.

-- 1° Achar médicos e clonar o conjunto
MED = rho MED1 pi nome, idade (Medicos)
MED2 = rho MED2 pi nome, idade (Medicos)

-- 2° Achar médicos que são mais velhos que alguém (bizarro mas é o jeito)
MED_MAIS_VELHOS_QUE_ALGUEM = pi MED1.nome, MED1.idade (sigma MED1.idade > MED2.idade (MED x MED2))

-- 3° O único médico que não é mais velho que ninguém é o mais novo
pi nome (MED - MED_MAIS_VELHOS_QUE_ALGUEM)

