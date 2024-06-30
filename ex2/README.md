# Exercise 2: CUP-based Parser for L Language

## Introduction

This exercise is the second part of our journey in building a compiler for the invented object-oriented language L. The goal of this assignment is to implement a CUP-based parser on top of the JFlex scanner created in Exercise 1. The parser will take a single text file containing an L program as input and produce a text file indicating whether the input program is syntactically valid. Additionally, the parser will internally create an abstract syntax tree (AST) for correctly formatted input programs.

## Prerequisites

- Java Development Kit (JDK)
- Graphviz
- JFlex
- CUP

### Installing Required Software

#### On macOS:

1. **Install Homebrew:**
   ```bash
   /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

