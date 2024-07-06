# Exercise 1: Compiler Implementation

## Introduction

This exercise is part of a semester-long project to implement a compiler for an object-oriented language called L. This README provides all the necessary information to complete the first exercise.

## Programming Assignment

The first exercise involves implementing a lexical scanner using the open-source tool JFlex. The scanner will take a single text file containing an L program as input and produce a tokenized representation of the input in a single text file.
   
## Lexical Considerations

- **Identifiers**: Contain letters and digits, must start with a letter.
- **Keywords**: Reserved keywords (e.g., class, nil, array) cannot be used as identifiers.
- **White Spaces**: Include spaces, tabs, and newline characters.
- **Comments**: 
  - Type-1: Begins with `//` followed by valid characters until a newline.
  - Type-2: Begins with `/*` and ends with `*/`.
- **Integers**: Sequences of digits, no leading zeros, 16-bit signed values.
- **Strings**: Sequences of letters within double quotes, no non-letter characters.

## Input

The input is a single text file containing the L program.

## Output

The output is a single text file with each token on a separate line, including the line number and character position. The output format for tokens like integers, identifiers, and strings is specified.

## Submission Guidelines

### Project Repository

1. Create a GitHub account and enable free private repositories.
2. Create a repository named `compilation` and invite collaborators.
3. Add the following files:
   - `ids.txt`: IDs of team members.
   - `users.txt`: GitHub usernames of team members.
   - `names.txt`: Hebrew names of team members.
4. Add a subfolder `ex1` for your code and include a makefile to build a runnable jar file named `LEXER`.

### Command-line Usage

The LEXER program takes two parameters:
1. Input file path
2. Output file path

### Skeleton

Use the provided makefile and skeleton code from the course repository:
- `jflex/LEX FILE.lex`
- `src/TokenNames.java`
- `src/Main.java`

Run the command in the `src/ex1` directory:
```sh
$ make
