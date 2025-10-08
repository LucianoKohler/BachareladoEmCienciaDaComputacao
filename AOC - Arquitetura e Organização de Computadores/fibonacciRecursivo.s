.text
.globl main

main:	                          #
	li $v0, 5	          #
	syscall                   # Lê int
	move $a0, $v0             # Arg a0 = n
                                  #
	jal fibo                  # *FIBONACCI* (salva em $v0)
	                          #
	move $a0, $v0             # 
	li $v0, 1                 # printa o output da função
	syscall                   #
	                          #
	jal end                   # Finaliza o programa
	                          #
fibo:	                          #
	blt $a0, 2, fibo_base     # Caso base da recursão n < 1
	                          #
	addi $sp, $sp, -12        # 3 words: $ra, fib(n-1) ($s0) e fib(n-2) ($s1)
	sw $ra, 0($sp)            #
	sw $s0, 4($sp)            #
	sw $s1, 8($sp)            #
	                          #
	move $s0, $a0             # Salva n em $s0
	                          #
	addi $a0, $a0, -1         # PRIMEIRA CHAMADA 
	jal fibo                  # Para enontrar fib(n-1)
	move $s1, $v0             #
	                          #
	addi $a0, $s0, -2         # SEGUNDA CHAMADA
	jal fibo                  # Para encontrar fib(n-2)
	                          #
	add $v0, $v0, $s1         # fib(n) = fib(n-1) + fib(n-2)
		                  #
	lw $ra, 0($sp)            # Salva todo o contexto
	lw $s0, 4($sp)            # 
	lw $s1, 8($sp)            #
	addi $sp, $sp, 12         # Volta a pilha pro estado original
	jr $ra                    # Volta pra função superior
	                          #
fibo_base:	                  #
	li $v0, 1                 # Caso base: $v0 = 1
	jr $ra	                  #
	                          #
end:                              #
	li $v0, 10                # Fim do programa
	syscall                   #
