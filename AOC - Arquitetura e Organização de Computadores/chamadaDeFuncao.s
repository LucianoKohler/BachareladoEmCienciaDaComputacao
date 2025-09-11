	.text
	.globl main
main:
	ori $a0, $zero, 1
	ori $a1, $zero, 2
	ori $a2, $zero, 3
	ori $a3, $zero, 4
	jal leaf_example
	
end:
	li $v0, 10
	syscall
	
leaf_example:
	add $s0, $a0, $a1
	add $s1, $a2, $a3
	sub $v0, $a0, $a1