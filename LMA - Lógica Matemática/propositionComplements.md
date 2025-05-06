# Subformulas of a Formula

Each formula can be represented by the **parts of a formula**, which will prove itself useful when we reach truth tables, look at one example:

**F1** = (P ^ Q) -> (¬R)

**subf(F1)** = {
P; Q; R; ¬R;
P ^ Q;
(P ^ Q) -> (¬R)
}

**note that Q -> (¬R) is NOT part of the subformulas, since Q is associated with (P ^ Q).*

To organize a formula properly, it is usually good to represent it in a graphical way (tree representation), watch the same formula in a more visual way:

          ->
         /  \
        ^     ¬
       / \    |
      P   Q   R

Q and R, for example, are separated on the image, which means that they are not a subformula

# Valoration of a formula
Means the values that a formula can assume (0 or 1)
### Examples
- A = (P v ¬P)
  - **V(A) = {1}** 
  - *Always True*

- B = (P ^ ¬P)
  - **V(B) = {0}**
  - *Always False*

- C = (A v B)
  - **V(C) = {0, 1}**
  - *Can be False or True*

With these valoration, we can give classifications to formulas:

### Satisfiable 
  - Can be proven right (can be True)
### Falsifiable
  - Can be proven wrong (can be False)
### Valid/Tautology
  - Is always True (can't be False)
### Unstatisfiable/Contradiction
  - Is always False (can't be True)

# Truth Tables

Solve this formula:

***p -> (¬q ^ ¬p) v (q ^ ¬q) -> (p v ¬q)***

:P

To make this easier, creating a ***Truth table*** can help separating the problems in parts