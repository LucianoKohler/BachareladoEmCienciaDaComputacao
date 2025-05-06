# Propositions

Propositions are sentences that can assume one of two values: True or False, these are widely used on the computer science/math areas to create allegations that may help to get to a research's objective.

propositions need to be **EXACT**, not having any ambiguities, having a clear and direct message while also being **ATOMIC**

There are also **COMPOST** propositions, which are sentences that contain more than one atomic proposition, but ideally, use only atomic propositions 👌

### *Examples*
---
<span style="color:lightblue">***The sky is blue***
- Atomic proposition

<span style="color:pink">***The sky is a little blue and sometimes orange, but never pink***
- Compost proposition

To make this proposition clearer, we can atomize it, which means making it simpler, and thus, easier to read

<span style="color:pink">***The sky is a little blue and sometimes orange, but never pink***
  - <span style="color:lightblue">***The sky is a little blue***
  - <span style="color:lightblue">***The sky is sometimes orange***
  - <span style="color:lightblue">***The sky cannot be pink***

Atomic, clear, and not ambiguous.

---

**NOTE:** We usually link a prop with a letter, for example, P, Q, R, S... In order to compact things and make formulas better to read, look at this formula:

***¬(A v B) ^ (C ^ D)***

Writing each sentence instead of a letter would be REALLY bad, so just use letters.

# Operators

Propositions can have characters that may change the result of a formula, those are:

### (¬)  Negation - Not
- Negation is simple, it reverses the value of a prop, if True, False, and if False, True.

### (^)  Conjunction - And
- And retruns True if BOTH of the values inside it are True, if one of them is False, no Trues for you.

### (v)  Disjunction - Or
- Or can have two meaning, the **inclusive** OR, which may be true if A, B or AB be true, and the **exclusive** OR, which will only be true if exactly ONE of the terms is True.

### (->) Implication - If, then
- The worst of them, Implication, is better explained with an example:
  - If it rains, I will grab an Umbrella

  - A = If it rains
  - B = I will grab an umbrella
  - A -> B

- If A is True, B will ALWAYS be True, but if A is False, B can be both True or False, since I didn't say anything about what will happen if it doesn't rain, I don't care :D

# Formulas

When combining Propositions with those characters, we can create **formulas**, which are a mathematical way to write them together, take a look at them:

***¬(A ^ B) v (C v ¬D)***

That's the basics about propositions, there are other topics to talk about, like:

- Priority of the operators
- Tree representation of a formula
- Brackets simplification

But I'll talk about them later on, for now, that's what a proposition is!
