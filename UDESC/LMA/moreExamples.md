## Given V(p) = 1 and V(q) = 0, discover:
  - p ^ ¬q = True
  - p v ¬q = True
  - ¬p ^ q = False
  - ¬p ^ ¬q = False
  - ¬p v ¬q = True
  - p ^ (¬p v q) = False

## Deduct V(p):

- V(q) = 1, V(p ^ q) = 0
- therefore p = 0
---
- V(q) = 0, V(p v q) = 0
- therefore, p = 0
---
- V(q) = 0, V(p -> q) = 0
- therefore, p = 1
---
- V(q) = 0, V(q ^ p) = 1
- therefore, impossible

## Deduct V(p) and V(q):

- V(p -> q) = 1, V(p ^ q) = 0
- therefore, V(p) = 0, V(q) = 0 or 1
---
- V(p -> q) = 1, V(p v q) = 0
- therefore, V(p) = 0, V(q) = 0 because of the second conditional
---
- V(p -> q) = 1, V(q -> p) = 1, V(p^q) = 1
- therefore, V(p) = 1, V(q) = 1 because of the third conditional
---

## Recognize if it is Satisfiable, Valid, Falsifiable and Unsatisfiable

- (p -> q) -> (q -> p)
  - Satisfiable if 11, 00 and 10, Falsifiable if 01
- (p ^ ¬p) -> q
  - Unsatisfiable, first conditional will always be false
- p -> q -> p ^ q
  - Satisfiable if 11 or 00, Falsifiable if 10 or 01
- ¬¬p -> p 
  - Valid
- ¬¬p -> p
  - Valid

## Find a way to satisfy the formulas:

- p -> ¬p
  - V(p) = 0
- q -> p ^¬p
  - V(q) = 0
- (p -> q) -> p
  - V(p) = 1, V(q) = 1 or 0
- ¬(p v q -> q)
  - V(p) = 1, V(q) = 0, remember the inversion!
- (p -> q) ^ (¬p -> ¬q)
  - all false or all true
  - its equal to p <-> q
- (p -> q) ^ (q -> p)
  - all false or all true
  - its equal to p <-> q