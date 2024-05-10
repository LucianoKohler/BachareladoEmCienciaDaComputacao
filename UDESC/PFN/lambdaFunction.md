# Lambda Functions
Lambda Functions are a brilliant and really hard to understand ways to declare functions at first glance, but throughout this MD you'll see the beauty of it.

LF (Lambda Function) were invented by Alonzo Church and it is a way to represent an algorithm by using an exotic syntax that just... Works! 

LF are the basis of a lot of algorithms and, if a programming language can replicate the behaviour of a Lambda Function, it can be called Turing Complete, since LF is strongly connected to Turing's Computer.

# Syntax
Take a look at this simple lambda function:
- λa.λb . (a b) 

**λ** is known as the character used to **declare variables**, and the variables without λ are the variables before declared and now **used** within the function 

Note that we need a "." after each variable.

# Alpha and Beta Reductions
They are ways to simplify/modify your **LF**
- **Alpha Reduction** is the **renaming** of variables, take the example:
  - (λx.λy . yx)
  - (λa.λb . ba)
  - Same function, different var names.

- **Beta Reduction** is the fitting of a variable inside the function, example:
  - (λa.λb . a b) (λc . c)
  - (λb .  (λc . c) b)
  - In the example above, we swapped **a** with our function **(λc . c)**, remember to always remove the λ declaration, since we don't need to declare what **already has** a value. 

# Numerals in Lambda Function

Numbers are also functions that we declare just like any other thing in **LF**, in these, **s** means Successor, and **z** means 0
```
0 = λs.λz . z 
1 = λs.λz . s(z) 
2 = λs.λz . s(s(z)) 
3 = λs.λz . s(s(s(z)))
. . .
```

# Boolean Values
Bools (True/False) are also included in Lambda.
```
True  = λx.λy . x
False = λx.λy . y
```

# Notable Functions

- **SUCCESSOR** = `λw.λy.λx . y(w y x)`
  - Example: Successor of 2:

  ```
  (λw.λy.λx . y(w y x)) (λs.λz . s(s(z)))
  ^     SUC. FUNC     ^ ^       2       ^

  (λy.λx . y((λs.λz . s(s(z))) y x))  
  Swapping λw with 2

  (λy.λx . y((λz . y(y(z))) x))  
  Inside the two, swapping λS by Y

  λy.λx . y(y(y(x)))
  Inside the two, swapping λZ by X
  ```
  Would you look at that, three in **LF**!
  
  ---

- **AND** = `λx.λy . xy(λuλv.v)`
- **OR**  = `λx.λy . (x(λuλv.u))y`
- **NOT** = `λx . (x(λuλv.v))(λa.λb.a)`
  - Example: True OR False

  ```
  (λx.λy . (x(λu.λv . u))y) (λx.λy . x) (λx.λy . y)
  ^          OR           ^ ^  TRUE   ^ ^  FALSE  ^

  (λy . ( (λx.λy . x) (λu.λv . u))y) (λx.λy . y)
  Swapping λx by True

  ((λx.λy . x) (λu.λv . u)) (λx.λy . y)
  Swapping λy by False
  
  (λy . (λu.λv . u))  (λx.λy . y)
  Swapping λx by True
  
  (λu.λv . u)
  Swapping λy by False (note that it disappears!)
  ```

  There, True OR False = True!

  ---



  
