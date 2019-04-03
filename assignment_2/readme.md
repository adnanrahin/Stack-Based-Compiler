CS 316    Spring 2019 
Observe course policies in undertaking this project. 
All programs must be written in Oracle Standard Edition compliant Java or ISO/ANSI standard compliant C++.
PROJECT 2: Top-Down Parser 
Due: 04/07/19, Sunday, 11 PM 
Late projects will not be accepted. 

This project is a continuation of Project 1. Implement a top-down parser for the following EBNF for our project language. 

⟨fun def list⟩ → { ⟨fun def⟩ }+ 
⟨fun def⟩ → ⟨header⟩ ⟨body⟩ 
⟨header⟩ → ⟨fun name⟩ "(" [ ⟨parameter list⟩ ] ")" 
⟨fun name⟩ → ⟨id⟩ 
⟨parameter list⟩ → ⟨parameter⟩ { "," ⟨parameter⟩ } 
⟨parameter⟩ → ⟨id⟩ 
⟨body⟩ → "{" ⟨s list⟩ "}" 
⟨s list⟩ → { ⟨statement⟩ } 
⟨statement⟩ → ⟨assignment⟩ | ⟨cond⟩ | ⟨while⟩ | ⟨block⟩ | ⟨fun call statement⟩ | ⟨print⟩ 
⟨assignment⟩ → ⟨var⟩ "=" ⟨right side⟩ ";" 
⟨var⟩ → ⟨id var⟩ | ⟨array var⟩ | "returnVal" 
⟨id var⟩ → ⟨id⟩ 
⟨array var⟩ → ⟨array name⟩ "[" ⟨E list⟩ "]" 
⟨array name⟩ → ⟨id⟩ 
⟨E list⟩ → ⟨E⟩ { "," ⟨E⟩ } 
⟨right side⟩ → ⟨array constructor⟩ | ⟨expr right side⟩ 
⟨array constructor⟩ → "new" "[" ⟨E list⟩ "]" 
⟨expr right side⟩ → ⟨expr⟩ 
⟨cond⟩ → "if" "(" ⟨expr⟩ ")" ⟨statement⟩ [ "else" ⟨statement⟩ ] 
⟨while⟩ → "while" "(" ⟨expr⟩ ")" ⟨statement⟩ 
⟨block⟩ → "{" ⟨s list⟩ "}" 
⟨fun call statement⟩ → ⟨fun call⟩ ";" 
⟨fun call⟩ → ⟨fun name⟩ "(" [ ⟨expr list⟩ ] ")" 
⟨expr list⟩ → ⟨expr⟩ { "," ⟨expr⟩ } 
⟨print⟩ → "print" ⟨expr⟩ ";" 
⟨expr⟩ → ⟨boolTerm⟩ { || ⟨boolTerm⟩ } 
⟨boolTerm⟩ → ⟨boolPrimary⟩ { && ⟨boolPrimary⟩ } 
⟨boolPrimary⟩ → ⟨E⟩ [ ⟨comp op⟩ ⟨E⟩ ] 
⟨comp op⟩ → "<" | "<=" | ">" | ">=" | "==" | "!=" 
⟨E⟩ → ⟨term⟩ { (+|−) ⟨term⟩ } 
⟨term⟩ → ⟨primary⟩ { (*|/) ⟨primary⟩ } 
⟨primary⟩ → ⟨var primary⟩ | ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | "(" ⟨expr⟩ ")" | − ⟨primary⟩ | ! ⟨primary⟩ | ⟨fun call primary⟩ 
⟨var primary⟩ → ⟨var⟩ 
⟨fun call primary⟩ → ⟨fun call⟩ 

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

Here's a sample set of test input/output files:
in1 | out1 
in2 | out2 
in3 | out3 
in4 | out4 
in5 | out5 
in6 | out6 
in7 | out7 
in8 | out8 
in9 | out9 
in10 | out10 
in11 | out11 
in12 | out12 
in13 | out13 
in14 | out14 
in15 | out15 
in16 | out16 
in17 | out17 
in18 | out18 
in19 | out19 
in20 | out20 
in21 | out21 
in22 | out22 
in23 | out23
You should make your own additional input files to test the program. Your outputs don't have to be identical to the samples, but they should display the parse tree structure clearly. 

Submission 

Email the following materials to keitaro.yukawa@gmail.com with the subject header: 

CS 316, Project 2, your full name
All the classes comprising your source code, including the lexical analyzer you used. Since there will be many classes, make sure to double check no classes are missing in your submission.
A list of all class names arranged like this page. This may be in text, html, PDF, or WORD file.
You may email the entire materials in a .zip or .rar compressed file. 

The due date is 04/07/19, Sunday, 11 PM. No late projects will be accepted. If you haven't been able to complete the project, you may send an incomplete program for partial credit. In this case, include a description of what is and is not working in your program along with what you believe to be the sources of the problems.
