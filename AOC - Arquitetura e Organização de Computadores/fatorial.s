.text
.globl main

main:
	li $v0, 5          
	syscall            # scanf n

	or $t0, $v0, $zero # t0 = n
	li $t1, 1          # t1 = 1
                           
loop:
	mul $t1, $t1, $t0  # t1 *= t0
	subi $t0, $t0, 1   # t0--
	bgtz $t0, loop     # t0 > 0 ? loop : continue
	
	or $a0, $zero, $t1 # a0 = t1
	li $v0, 1          # print a0
	syscall
	