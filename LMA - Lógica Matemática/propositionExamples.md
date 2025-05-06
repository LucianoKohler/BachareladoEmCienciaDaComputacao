# Simple Formula Representations

- The navy has 4 aircraft carriers
  - **A**

- Although it's summer, we haven't got a hot day
  - A = It's summer
  - B = we haven't got a hot day
    - **A ^ ¬B**

- My son will be a politician or a soccer player
  - A = My son will be a politician
  - B = My son will be a soccer player
    - **A v B**

- The white car is too expensive and its insurance is not cheap
  - A = The white car is too expensive
  - B = The white car's insurance is not cheap
    - **A ^ ¬B**

- The world will be saved by the atomic bomb or by the diplomacy
  - A = The world will be saved by the atomic bomb
  - B = The world will be saved by the diplomacy
    - **A v B**

# Implication Examples
- If the rain continues, the river will overflow
  - A = The rain continues
  - B = The river overflows
    - **A -> B**

- Maria sells the car if she buys the house
  - A = Maria sells the car
  - B = Maria buys the house
    - **B -> A**

- The avocados are only ripe when they are dark and soft
  - A = The avocados are ripe
  - B = The avocados are dark
  - C = The avocados are soft
    - **(B ^ C) -> A**

# Tree Representations

- **¬(p v ¬q ^ s)**

        ¬
        |
        v
       / \
      p   ^
         / \
        ¬   s
        |
        q

- **(¬p -> q) -> (q -> ¬p)**

          ->
         /   \
        ->   ->
       /  \ /  \
      ¬   q q   ¬
      |         |
      p         p

- **(p ^ q) ^ ¬(r v s)**

           ^
         /   \
        ^     ¬
       / \    |
      p   q   v
             / \
            r   s

# Removing Unnecessary Brackets

- ((A v B) v (C v A))
  - **A v B v (C v A)**

- ¬(p v (q ^ r))
  - **¬(p v q ^ r)**

- ((A) ^ (¬(q) v B ^ x))
  - **A ^ (¬q v B ^ x)**

# Subformulas
*Note that there might be many single formulas in one line, they are separate, I just included them in the same line to save space.
- ¬p -> q
  - p, q
  - ¬p 
  - ¬p -> q

- p v q
  - p, q
  - p v q

- p ^ ¬q ^ r ^ ¬s
  - p, q, r, s
  - ¬q
  - ¬s
  - p ^ ¬q
  - p ^ ¬q ^ r
  - p ^ ¬q ^ r ^ ¬s