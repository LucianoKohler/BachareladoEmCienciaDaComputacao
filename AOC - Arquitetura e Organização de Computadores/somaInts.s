  .text
  .globl main

main:
  li $t0, 3           # load immediate em $t0
  li $t1, 6           # load immediate em $t1
    add $a0, $t0, $t1 # soma

  li $v0, 1           # syscall 1 = print int
  syscall             # imprime o valor em $a0

  li $v0, 10          # syscall 10 = exit
  syscall             # encerra o programa
