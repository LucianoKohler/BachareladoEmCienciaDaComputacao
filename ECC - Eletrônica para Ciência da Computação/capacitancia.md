# Capacitância e Capacitores
Um capacitor nada mais é que um "nobreak", que segura uma certa energia e, ao desligar do circuito, se descarregará para manter o circuito ativo por mais um tempo.

Exuistem dois tipos: 
- Eletrolítico: tem polarização, parece um Tentacool
- Cerâmico: Não tem polarização, parece um resistor

A quantidade de carga que um capacitor pode armazenar é chamada de capacitância, que vai do + para o -
Utiliza farad, que é a carga de um Coulomb

# Fórmulas

C = Q/V
Capacitância é a carga dividido pela tensão

E = V/d
Intensidade do campo elétrico é a tensão dividido por...

## Fórmulas de Carga

Vc(t) = V0 * (1 - e^(t/RC))

Ic(t) = Vo/R * e^(-t/RC)

- Vc(t): Carga de um capacitor no tempo t
- Ic(t): Corrente de um capacitor no tempo t
- V0: Voltagem inicial do circuito
- t: Tempo em segundos
- RC: Resistência vezes capacitância do circuito

---

## Fórmulas de Descarga

Vc(t) = V0 * e^(-t/RC)

Ic(t) = - Vo/R * e^(-t/RC)
