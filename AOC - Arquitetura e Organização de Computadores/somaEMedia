.data
erro_str: .asciiz "ERRO"

.text
.globl main

main:
	li $t0, 0    # qtdInputs = 0
	li $t1, 0    # sum = 0
	
in:
	li $v0, 5
	syscall           # scanf n
	
	add $t1, $t1, $v0 # sum += $v0
	addi $t0, $t0, 1  # qtdInputs++
	bne $v0, -1, in   # $v0 != -1 ? volta pro in
                         
processing:
	addi $t0, $t0, -1 # Diminui 1 para balancear o 1 somado
	addi $t1, $t1, 1  # Soma 1 para balancear o -1 somado
	
	div $t1, $t0      # resultado em LO

out:
	move $a0, $t1     
	li $v0, 1
	syscall           # printa a soma em a0
	
	li $v0, 11        # print char em a0
	li $a0, 10        # 10 = ASCII para '\n'
	syscall
	
	beq $t0, 0, error # Se não recebemos inputs, vai dividir por 0 (erro)
	li $v0, 1         # printa media em a0 (int)
	mflo $a0
	syscall
	
	b end             # Ir para o fim
		
error: 
	li $v0, 4         # Print string
	la $a0, erro_str  # Aponta para "ERRO"
	syscall
	b end             # Ir para o fim

end:
	li $v0, 10         # fim
	syscall
