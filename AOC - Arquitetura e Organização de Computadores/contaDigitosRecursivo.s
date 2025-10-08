.text
.globl main

main:                                #
	li $v0, 5                    #
	syscall                      #
	move $a0, $v0                # $a0 = Numero a ser analisado
                                     # 
	beqz $a0, end                # Se for 0, para de ler valores
                                     # 	
	li $v0, 5                    #
	syscall                      #
	move $a1, $v0                # $a1 = Numero procurado
                                     #
	jal contaDigitos             # CHAMADA DA FUNÇÃO
                                     #
	move $a0, $v0                # Printa o output da função
	li $v0, 1                    #
	syscall                      #
	                             #
	li $a0, 10                   # printa \n
	li $v0, 11                   #
	syscall                      #
                                     #
	j main                       # Volta para continuar o loop
                                     #
contaDigitos:                        #
	beqz $a0, contaDigitosParada # Caso base (n=0)
	                             #
	addi $sp, $sp, -8            # Reserva duas words, uma para $ra 
	sw $ra, 0($sp)               # e outra para se o dígito é igual ou não
	sw $s0, 4($sp)               #
	                             #
	li $t0, 10                   #
	div $a0, $t0                 # Divide por 10
	mflo $a0                     # Quociente
	mfhi $t1                     # Resto
	seq $s0, $t1, $a1            # Se $t1 = $a1, $s0 = 1, se não, é 0
                                     #
	jal contaDigitos             # Recursão
                                     #	
	add $v0, $v0, $s0            # Soma 1 ou 0 na quantidade de dígitos
                                     #
	lw $ra, 0($sp)               # Recebe o contexto
	lw $s0, 4($sp)               #
	addi $sp, $sp, 8             # Volta a pilha
	jr $ra                       # Sai da função
                                     #
contaDigitosParada:                  #
	li $v0, 0                    # Com n=0, nunca temos dígitos iguais, logo 0
	jr $ra                       # Volta
                                     #
end:                                 #
	li $v0, 10                   # 
	syscall                      # Fim do programa
