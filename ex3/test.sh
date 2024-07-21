#!/bin/bash

# Directories
INPUT_DIR="input"
EXPECTED_OUTPUT_DIR="expected_output"
OUTPUT_DIR="output1"

# Ensure the output folder exists
mkdir -p "$OUTPUT_DIR"

for input_file in "$INPUT_DIR"/*; do
    # Extract file name without extension
    file_name=$(basename "$input_file")
    file_name_no_ext="${file_name%.*}"

    # Construct expected output file path
    expected_output_file="${EXPECTED_OUTPUT_DIR}/${file_name_no_ext}_Expected_Output.txt"

    # Run the program with the input file and redirect output to a temporary file
    java -jar COMPILER "$input_file" "$OUTPUT_DIR/$file_name"
done


