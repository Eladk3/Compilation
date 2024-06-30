#!/bin/bash

input_folder="testing/input"
expected_output_folder="testing/expected_output"
output_folder="output"

# Ensure the output folder exists
mkdir -p "$output_folder"

for input_file in "$input_folder"/*; do
    # Extracting the file name without the path
    file_name=$(basename "$input_file")
    file_name_no_ext="${file_name%.*}"

    # Constructing the expected output file name based on the input file name
    expected_output_file="$expected_output_folder/${file_name_no_ext}_Expected_Output.txt"


    # Running the lexer and redirecting the output to a temporary file
    java -jar LEXER "$input_file" "$output_folder/tmp.txt"

    # Comparing the generated output with the expected output
    generated_output_file="$output_folder/tmp.txt"

    if [[ $file_name == *ERROR* ]]; then
        if [[ $(wc -w < "$generated_output_file") -eq 1 && $(cat "$generated_output_file") == "ERROR" ]]; then
            echo "Test Passed: $file_name"
        else
            echo "Test Failed: $file_name - Invalid content in generated output"
            cat "$generated_output_file"
        fi
    else
        if cmp -s "$expected_output_file" "$generated_output_file"; then
            echo "Test Passed: $file_name"
        else
            echo "Test Failed: $file_name"
            cmp "$expected_output_file" "$generated_output_file"
        fi
    fi
done

# Clean up temporary files
rm -f "$output_folder/tmp.txt"
