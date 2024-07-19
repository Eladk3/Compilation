# Compiler Construction for the L Language

Welcome to the repository for the compiler construction of the object-oriented language L. This repository contains three exercises that guide you through building a compiler using tools like JFlex and CUP.

## Exercises

### Exercise 1: Lexical Analysis
- **Due Date:** 20/6/2024
- **Description:** Implement a lexical scanner using JFlex. The input is a single text file containing an L program, and the output is a tokenized representation of the input.
- **Details:** [Exercise 1 PDF](path_to_ex1_pdf)

### Exercise 2: Syntax Analysis
- **Due Date:** 4/7/2024
- **Description:** Implement a CUP-based parser on top of the JFlex scanner from Exercise 1. The input is a single text file containing an L program, and the output indicates whether the program is syntactically valid.
- **Details:** [Exercise 2 PDF](path_to_ex2_pdf)

### Exercise 3: Semantic Analysis
- **Due Date:** 25/7/2024
- **Description:** Implement a semantic analyzer that checks for semantic errors in the abstract syntax tree (AST) produced by the parser. The input is a single text file containing an L program, and the output indicates whether the program is semantically valid.
- **Details:** [Exercise 3 PDF](path_to_ex3_pdf)

## Usage

### Building and Running

#### Exercise 1
1. Navigate to the `ex1` directory.
2. Run `make` to build the lexical scanner.
3. Use `./LEXER input_file output_file` to tokenize the input file.

#### Exercise 2
1. Navigate to the `ex2` directory.
2. Run `make` to build the parser.
3. Use `./PARSER input_file output_file` to parse the input file.

#### Exercise 3
1. Navigate to the `ex3` directory.
2. Run `make` to build the semantic analyzer.
3. Use `./COMPILER input_file output_file` to analyze the input file.

## Requirements
- Java 8
- JFlex
- CUP
- Graphviz (for visualizing ASTs)

## Additional Resources
- [JFlex Documentation](http://jflex.de/)
- [CUP Documentation](http://www2.cs.tum.edu/projects/cup/)
- [GitHub Guide](https://guides.github.com/activities/hello-world/)
