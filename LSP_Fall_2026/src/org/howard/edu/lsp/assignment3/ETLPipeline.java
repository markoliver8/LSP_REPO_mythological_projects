package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.List;

public class ETLPipeline {

    public static void main(String[] args) {

        String inputFile = "data/employees.csv";
        String outputFile = "data/transformed_employees.csv";

        EmployeeReader reader = new EmployeeReader();
        EmployeeTransformer transformer = new EmployeeTransformer();
        EmployeeWriter writer = new EmployeeWriter();

        List<Employee> employees;

        try {

            // Extract
            employees = reader.readEmployees(inputFile);

            // Transform
            transformer.transform(employees);

            // Load
            writer.writeEmployees(outputFile, employees);

        } catch (IOException e) {

            System.out.println(
                    "Error reading or writing files: "
                    + e.getMessage()
            );

            return;
        }

        int rowsRead = reader.getRowsRead();
        int rowsTransformed = employees.size();
        int rowsSkipped = reader.getRowsSkipped();

        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output file: " + outputFile);
    }
}