# CSV to SQL Converter

## Overview

This program serves to streamline the process of converting CSV (Comma-Separated Values) files into SQL (Structured Query Language) code. By providing a straightforward solution, it aims to simplify the task of importing data from CSV files into SQL databases.

## Features

- **Effortless Conversion**: Easily transform your CSV data into SQL format with a few simple steps.
- **Flexible Input**: Accepts CSV files with a predefined format, ensuring compatibility and ease of use.
- **Scalable**: Handle large datasets by processing as many lines as needed, as long as they adhere to the specified format.

## Usage

1. Prepare your CSV file in the following format:
```ProductName,SKU,Quantity,DateAdded,Color,Size
Shirt,SKU00001,10,2022-03-15,Green,L
Shirt,SKU00002,5,2022-03-16,Red,M
Pants,SKU00003,2,2022-03-17,Green,M
Hat,SKU00004,8,2022-03-18,Black,XL
Pants,SKU00005,12,2022-03-19,Blue,S```

Ensure each line adheres to the specified structure: ProductName, SKU, Quantity, DateAdded, Color, Size.

2. Run the program, providing the CSV file as input.

3. Retrieve the generated SQL code, ready for use in your database.

## Notes

- Ensure your CSV file strictly adheres to the specified format for accurate conversion.
- For larger datasets, the program efficiently handles processing, accommodating as many lines as necessary.
