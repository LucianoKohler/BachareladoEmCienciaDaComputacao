# Gerado pelo Chat GPT para entender
.data
newline: .asciiz "\n"

.text
.globl main

# --------------------------
# main: lê num e k e chama a função recursiva
# --------------------------
main:
    # lê num
    li $v0, 5
    syscall
    move $a0, $v0      # $a0 = num

    # lê k
    li $v0, 5
    syscall
    move $a1, $v0      # $a1 = k

    # chama a função recursiva
    jal count_digits

    # imprime resultado
    li $v0, 1
    move $a0, $v0
    syscall

    # imprime newline
    li $v0, 4
    la $a0, newline
    syscall

    # fim do programa
    li $v0, 10
    syscall

# --------------------------
# count_digits: função recursiva
# parâmetros:
#   $a0 = num
#   $a1 = k
# retorno:
#   $v0 = quantidade de dígitos k em num
# --------------------------
count_digits:
    # caso base: num == 0
    beqz $a0, base_case

    # divide num por 10
    li $t0, 10
    div $a0, $t0
    mfhi $t1       # último dígito
    mflo $a0       # quociente, novo num

    # chama recursivamente
    jal count_digits

    # $v0 agora tem o resultado da chamada recursiva
    # incrementa se último dígito == k
    beq $t1, $a1, increment
    jr $ra         # retorna sem incrementar

increment:
    addi $v0, $v0, 1
    jr $ra

base_case:
    li $v0, 0      # nenhum dígito restante
    jr $ra
