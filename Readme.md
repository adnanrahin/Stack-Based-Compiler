This project is a continuation of Project 1. Implement a top-down parser for the following EBNF for our project language. 

 ⟨fun def list⟩ → { ⟨fun def⟩ }+ \
 ⟨fun def⟩ → ⟨header⟩ ⟨body⟩ \
 ⟨header⟩ → ⟨fun name⟩ "(" [ ⟨parameter list⟩ ] ")" \
 ⟨fun name⟩ → ⟨id⟩ \
 ⟨parameter list⟩ → ⟨parameter⟩ { "," ⟨parameter⟩ } \
 ⟨parameter⟩ → ⟨id⟩ \
 ⟨body⟩ → "{" ⟨s list⟩ "}" \
 ⟨s list⟩ → { ⟨statement⟩ } \
 ⟨statement⟩ → ⟨assignment⟩ | ⟨cond⟩ | ⟨while⟩ | ⟨block⟩ | ⟨fun call statement⟩ | ⟨print⟩ \
 ⟨assignment⟩ → ⟨var⟩ "=" ⟨right side⟩ ";" \
 ⟨var⟩ → ⟨id var⟩ | ⟨array var⟩ | "returnVal" \
 ⟨id var⟩ → ⟨id⟩ \
 ⟨array var⟩ → ⟨array name⟩ "[" ⟨E list⟩ "]" \ 
 ⟨array name⟩ → ⟨id⟩ \ 
 ⟨E list⟩ → ⟨E⟩ { "," ⟨E⟩ } \
 ⟨right side⟩ → ⟨array constructor⟩ | ⟨expr right side⟩ \
 ⟨array constructor⟩ → "new" "[" ⟨E list⟩ "]" \
 ⟨expr right side⟩ → ⟨expr⟩ \
 ⟨cond⟩ → "if" "(" ⟨expr⟩ ")" ⟨statement⟩ [ "else" ⟨statement⟩ ] \
 ⟨while⟩ → "while" "(" ⟨expr⟩ ")" ⟨statement⟩ \
 ⟨block⟩ → "{" ⟨s list⟩ "}" \
 ⟨fun call statement⟩ → ⟨fun call⟩ ";" \
 ⟨fun call⟩ → ⟨fun name⟩ "(" [ ⟨expr list⟩ ] ")" \
 ⟨expr list⟩ → ⟨expr⟩ { "," ⟨expr⟩ } \
 ⟨print⟩ → "print" ⟨expr⟩ ";" \
 ⟨expr⟩ → ⟨boolTerm⟩ { || ⟨boolTerm⟩ } \
 ⟨boolTerm⟩ → ⟨boolPrimary⟩ { && ⟨boolPrimary⟩ } \
 ⟨boolPrimary⟩ → ⟨E⟩ [ ⟨comp op⟩ ⟨E⟩ ] \
 ⟨comp op⟩ → "<" | "<=" | ">" | ">=" | "==" | "!=" \
 ⟨E⟩ → ⟨term⟩ { (+|−) ⟨term⟩ } \
 ⟨term⟩ → ⟨primary⟩ { (*|/) ⟨primary⟩ } \
 ⟨primary⟩ → ⟨var primary⟩ | ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | "(" ⟨expr⟩ ")" | − # ⟨primary⟩ | ! ⟨primary⟩ | ⟨fun call primary⟩ \
 ⟨var primary⟩ → ⟨var⟩ \
 ⟨fun call primary⟩ → ⟨fun call⟩ \

NOTE: In the 2-branch conditionals, each "else" matches the closest preceding unmatched "if". 
NOTE: The binary operators +, −, *, /, ||, && associate to left. 

A lexical analyzer for this language's tokens has been implemented in Project 1. 

The category ⟨expr⟩ contains Boolean, comparison, and arithmetic expressions with the following precedence (listed from highest to lowest):
  !, unary −    highest precedence
  *, /
  +, binary −
  <, <=, >, >=, ==, !=
  &&
  ||      lowest precedence
The grammar for ⟨expr⟩ allows mistyped expressions like "!316" and "a+b || a−c". If the source language is statically typed, such type errors would be caught by the compiler's type checking phase. Our language is type-free so type errors will result in runtime errors. 

Your program is to read any text file that contains (what is intended to be) a string in the category ⟨fun def list⟩. It will then construct an explicit parse tree and display it in linearly indented form: each syntactic category name in the parse tree is displayed on a separate line, prefixed with the integer i representing its depth and indented by i blanks. This is a basic form of syntax profiler. 

Construct explicit parse trees by class objects using the method described in lecture. Recall that in this method, for each production rule with alternatives: 

   ⟨X⟩ → α1 | ··· | αn,    n ≥ 2 

the class for ⟨X⟩ is abstract and the classes for αi are subclasses of it. Class fields represent sub parse trees. 

The above EBNF defines all linear lists of syntactic categories by the iterative constructs { … } or { … }+. Implement these linear lists by linked lists. (I've used java.util.LinkedList.) 

Note that ⟨fun call statement⟩ begins with ⟨id⟩ and so does ⟨assignment⟩ if it begins with ⟨id var⟩ or ⟨array var⟩. These three cases can be distinguished by the next token "(" for ⟨fun call statement⟩ and "[" for ⟨array var⟩. Likewise for ⟨fun call primary⟩ and ⟨var primary⟩. 

Display parse trees by printParseTree functions defined in syntactic-category classes. 

This sample parser is an example of construction of explicit parse trees by class objects. The printParseTree functions display parse trees in linearly indented form. In this project you may ignore the classes Interpreter, Compiler, Val and the functions M, Eval, emitInstructions. You may build your parser based on the structure of this sample parser. 

An appropriate error message should be issued when the first syntax error is found; in this project there is no need to recover from it and continue parsing. (Real-world compilers do some type of syntax-error recovery and attempt to find more syntax errors.) 

To make grading efficient and uniform, observe the following:
The program must read the input/output file names as external arguments to the main function. How to set external arguments to Java main function in Eclipse.
If Java is used, the main function to be invoked to run the program must be included in Parser.java class.
If your Project 1 lexical analyzer wasn't correct, you may use this sample lexical analyzer: State.java, LexAnalyzer.java. 

