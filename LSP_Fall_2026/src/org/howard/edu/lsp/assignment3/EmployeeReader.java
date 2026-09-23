package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EmployeeReader {

    private int rowsRead = 0;
    private int rowsSkipped = 0;

    public List<Employee> readEmployees(String inputFile) throws IOException {

        List<Employee> employees = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(inputFile))) {

            // Read and ignore header
            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                rowsRead++;

                // Skip blank lines
                if (line.trim().isEmpty()) {
                    rowsSkipped++;
                    continue;
                }

                String[] fields = line.split(",", -1);

                // Must contain exactly 5 fields
                if (fields.length != 5) {
                    rowsSkipped++;
                    continue;
                }

                // Trim every field
                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].trim();
                }

                String employeeIdText = fields[0];
                String name = fields[1].toUpperCase();
                String department = fields[2];
                String hoursWorkedText = fields[3];
                String hourlyRateText = fields[4];

                int employeeId;
                BigDecimal hoursWorked;
                BigDecimal hourlyRate;

                // Validate EmployeeID
                try {
                    employeeId = Integer.parseInt(employeeIdText);
                } catch (NumberFormatException e) {
                    rowsSkipped++;
                    continue;
                }

                // Validate HoursWorked
                try {
                    hoursWorked = new BigDecimal(hoursWorkedText);
                } catch (NumberFormatException e) {
                    rowsSkipped++;
                    continue;
                }

                // Validate HourlyRate
                try {
                    hourlyRate = new BigDecimal(hourlyRateText);
                } catch (NumberFormatException e) {
                    rowsSkipped++;
                    continue;
                }

                // HoursWorked and HourlyRate cannot be negative
                if (hoursWorked.compareTo(BigDecimal.ZERO) < 0
                        || hourlyRate.compareTo(BigDecimal.ZERO) < 0) {

                    rowsSkipped++;
                    continue;
                }

                Employee employee = new Employee(
                        employeeId,
                        name,
                        department,
                        hoursWorked,
                        hourlyRate
                );

                employees.add(employee);
            }
        }

        return employees;
    }

    public int getRowsRead() {
        return rowsRead;
    }

    public int getRowsSkipped() {
        return rowsSkipped;
    }
}