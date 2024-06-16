# Compilation Project - Exercise 1

## Introduction
This project is part of a course assignment to implement a compiler for an object-oriented language called L. The first exercise involves creating a lexical scanner using JFlex.

## Programming Assignment
The objective of this exercise is to implement a lexical scanner based on the open-source tool JFlex. The scanner will take a single text file containing an L program as input and produce a text file with a tokenized representation of the input.

## Lexical Considerations
- **Identifiers:** Contain letters and digits, must start with a letter.
- **Keywords:** Reserved keywords (`class`, `nil`, `array`, `while`, `int`, `void`, `extends`, `return`, `new`, `if`, `string`) cannot be used as identifiers.
- **White Spaces:** Include spaces, tabs, and newline characters.
- **Comments:** 
  - Type-1: `//` followed by any character up to a newline.
  - Type-2: `/*` followed by any characters up to `*/`.
- **Integers:** Sequences of digits, no leading zeroes, must be within 16-bit signed values (0 to 32767).
- **Strings:** Sequences of letters between double quotes. Strings with non-letter characters or unclosed strings are lexical errors.

## Input
The input is a single text file containing the L program.

## Output
The output is a single text file with a tokenized representation of the input program. Each token should be on a separate line with the line number and character position. If a lexical error occurs, the output file should contain only the word `ERROR`.

### Token Names
Refer to the table in the PDF for the token names. Tokens associated with values include integers, identifiers, and strings.

### Example Token Output
- `LPAREN[7,8]`
- `INT(74)[3,8]`
- `STRING("Dan")[2,5]`
- `ID(numPts)[1,6]`

## Submission Guidelines
1. **Project Repository:** Create a private repository named `compilation` on GitHub. Add collaborators `galvien` and `davidtr1037`.
2. **Files to Include:**
   - `ids.txt`: IDs of all team members (one per line).
   - `users.txt`: GitHub usernames of all team members (one per line).
   - `names.txt`: Hebrew names of all team members (one per line).
3. **Folder Structure:**
   - `compilation/ex1`: Subfolder containing the exercise code and a `Makefile` to build the source files into a runnable JAR file named `LEXER`.
4. **Build and Run:**
   - Ensure the exercise compiles and runs on the official course server (`nova.cs.tau.ac.il`).

### Command-line Usage
The `LEXER` receives two parameters:
- `input` (input file path)
- `output` (output file path containing the tokenized output)

### Environment Requirements
- **OS:** Ubuntu 14.04 / 16.04
- **Java:** Java 8

### Skeleton
Use the provided Makefile and skeleton code available in the course repository:
- [Skeleton Code](https://github.com/davidtr1037/compilation-tau/tree/master/src/ex1)

To build and run the skeleton:
```sh
$ make
