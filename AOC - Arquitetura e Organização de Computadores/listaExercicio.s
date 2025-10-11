.text
.globl main

main:
	li $t1, 10
	li $t2, 3
	
	sll $t2, $t2, 1
	add $a0, $t1, $t2
	
	ori $v0, $zero, 1
	syscall
	
	ori $v0, $zero, 10
	syscall