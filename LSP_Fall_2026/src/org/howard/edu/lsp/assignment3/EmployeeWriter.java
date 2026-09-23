package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EmployeeWriter {

    public void writeEmployees(
            String outputFile,
            List<Employee> employees) throws IOException {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(outputFile))) {

            writer.write(
                    "EmployeeID,Name,Department,HoursWorked,HourlyRate,"
                    + "GrossPay,PayLevel,EmploymentStatus"
            );

            writer.newLine();

            for (Employee employee : employees) {

                writer.write(employee.toCSV());
                writer.newLine();
            }
        }
    }
}