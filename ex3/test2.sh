#!/bin/bash

# Directories
INPUT_DIR="input"
EXPECTED_OUTPUT_DIR="expected_output"
OUTPUT_DIR="output"

# Ensure the output folder exists
mkdir -p "$OUTPUT_DIR"

# Create a temporary file to store test results
temp_file="testout.txt"

# Loop through each input file
for input_file in "$INPUT_DIR"/*; do
    # Extract file name without extension
    file_name=$(basename "$input_file")
    file_name_no_ext="${file_name%.*}"

    # Construct expected output file path
    expected_output_file="${EXPECTED_OUTPUT_DIR}/${file_name_no_ext}_Expected_Output.txt"

    # Run the program with the input file and redirect output to a temporary file
    java -jar COMPILER "$input_file" "$OUTPUT_DIR/SemanticStatus.txt"

    # Check if the test file contains "ERROR"
    if [[ $file_name == *ERROR* ]]; then
        # Check if generated output is "ERROR"
        if [[ $(wc -w < "$OUTPUT_DIR/SemanticStatus.txt") -eq 1 && $(cat "$OUTPUT_DIR/SemanticStatus.txt") == "ERROR" ]]; then
            echo "Test Passed: $file_name" >> "$temp_file"
        else
            echo "Test Failed: $file_name - Invalid content in generated output" >> "$temp_file"
            cat "$OUTPUT_DIR/SemanticStatus.txt" >> "$temp_file"
        fi
    else
        # Compare generated output with expected output
        if cmp -s "$expected_output_file" "$OUTPUT_DIR/SemanticStatus.txt"; then
            echo "Test Passed: $file_name" >> "$temp_file"
        else
            echo "Test Failed: $file_name" >> "$temp_file"
            cat "$expected_output_file" 
            cat "$OUTPUT_DIR/SemanticStatus.txt"
            cmp "$expected_output_file" "$OUTPUT_DIR/SemanticStatus.txt" >> "$temp_file"
        fi
    fi
done

# Clean up temporary files
rm -f "$OUTPUT_DIR/SemanticStatus.txt"

echo "Test results are stored in $temp_file"
