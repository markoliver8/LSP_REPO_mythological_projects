 Assignment #3 Design Discussion

 Assignment #2 Design

The Assignment #2 solution was organized as a single `ETLPipeline` class. The class was responsible for reading the input CSV file, validating employee records, transforming employee data, calculating gross pay, determining pay level and employment status, and writing the transformed CSV file. The `main` method contained most of the program's logic.

Assignment #3 Design Changes

For Assignment #3, I refactored the program using object-oriented design principles and separated the responsibilities of the original `ETLPipeline` class into several meaningful classes.

The `Employee` class represents an employee and encapsulates employee data and employee-specific behavior, including gross pay, pay level, employment status, and CSV formatting.

The `EmployeeReader` class is responsible for reading the input CSV file and validating employee records. The `EmployeeTransformer` class is responsible for applying the transformation operations to valid employees. The `EmployeeWriter` class is responsible for generating the transformed CSV output.

The `ETLPipeline` class now acts primarily as the controller for the ETL process. It coordinates the reader, transformer, and writer instead of performing all of the processing itself.

 Why the Design Is an Improvement

The Assignment #3 design separates responsibilities into focused classes instead of placing the entire ETL process in one class. This makes the program easier to understand, maintain, and modify. For example, changes to CSV output can be made in `EmployeeWriter` without changing the file-reading or transformation logic. Similarly, changes to employee calculations can be made in `Employee` without modifying the pipeline controller.

The refactored design also uses encapsulation by keeping employee data and employee-related behavior together in the `Employee` class. Overall, the new design provides a clearer separation of responsibilities while preserving the functional behavior and output of Assignment #2.

https://chatgpt.com/share/6ab40d4e-c464-83ea-9b48-148332b7bab5
I used AI to implement the object oriented design to my assignment 2 code. I proof read the code and the Design.md and made adjustments to fit my liking. 
