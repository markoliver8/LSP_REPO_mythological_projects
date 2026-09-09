package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {

    public static void main(String[] args) {

        String inputFile = "data/employees.csv";
        String outputFile = "data/transformed_employees.csv";

        int rowsRead = 0;
        int rowsTransformed = 0;
        int rowsSkipped = 0;

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {

            // Read the header
            String header = reader.readLine();

            writer.write(
                "EmployeeID,Name,Department,HoursWorked,HourlyRate,"
                + "GrossPay,PayLevel,EmploymentStatus"
            );
            writer.newLine();

            String line;

            while ((line = reader.readLine()) != null) {

                // Every non-header line counts as a row read,
                // including blank and malformed rows.
                rowsRead++;

                // Skip blank lines
                if (line.trim().isEmpty()) {
                    rowsSkipped++;
                    continue;
                }

                // Split the CSV row
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

                /*
                 * Calculate gross pay.
                 *
                 * If hours <= 40:
                 *     all hours are regular hours.
                 *
                 * If hours > 40:
                 *     first 40 hours are regular
                 *     remaining hours are overtime at 1.5x.
                 */
                BigDecimal regularHours;
                BigDecimal overtimeHours;

                if (hoursWorked.compareTo(new BigDecimal("40")) <= 0) {
                    regularHours = hoursWorked;
                    overtimeHours = BigDecimal.ZERO;
                } else {
                    regularHours = new BigDecimal("40");
                    overtimeHours = hoursWorked.subtract(new BigDecimal("40"));
                }

                BigDecimal regularPay =
                    regularHours.multiply(hourlyRate);

                BigDecimal overtimePay =
                    overtimeHours
                        .multiply(hourlyRate)
                        .multiply(new BigDecimal("1.5"));

                BigDecimal grossPay =
                    regularPay.add(overtimePay);

                /*
                 * IT bonus:
                 * Department must exactly equal "IT"
                 * after trimming.
                 */
                if (department.equals("IT")) {
                    grossPay = grossPay.multiply(new BigDecimal("1.05"));
                }

                // Round GrossPay using round-half-up to exactly 2 decimals
                grossPay = grossPay.setScale(2, RoundingMode.HALF_UP);

                // Determine PayLevel based on final rounded GrossPay
                String payLevel;

                if (grossPay.compareTo(new BigDecimal("500")) < 0) {
                    payLevel = "Low";
                } else if (grossPay.compareTo(new BigDecimal("1000")) < 0) {
                    payLevel = "Standard";
                } else if (grossPay.compareTo(new BigDecimal("2000")) < 0) {
                    payLevel = "High";
                } else {
                    payLevel = "Executive";
                }

                // Determine employment status
                String employmentStatus;

                if (hoursWorked.compareTo(new BigDecimal("30")) < 0) {
                    employmentStatus = "Part-Time";
                } else {
                    employmentStatus = "Full-Time";
                }

                /*
                 * HourlyRate is rounded only for output.
                 * The original value was used in the calculations.
                 */
                BigDecimal outputHourlyRate =
                    hourlyRate.setScale(2, RoundingMode.HALF_UP);

                BigDecimal outputHoursWorked =
                    hoursWorked.setScale(2, RoundingMode.HALF_UP);

                // Write transformed row
                writer.write(
                    employeeId + ","
                    + name + ","
                    + department + ","
                    + outputHoursWorked.toPlainString() + ","
                    + outputHourlyRate.toPlainString() + ","
                    + grossPay.toPlainString() + ","
                    + payLevel + ","
                    + employmentStatus
                );

                writer.newLine();

                rowsTransformed++;
            }

        } catch (IOException e) {
            System.out.println("Error reading or writing files: "
                    + e.getMessage());
            return;
        }

        // Required console summary
        System.out.println("Rows read: " + rowsRead);
        System.out.println("Rows transformed: " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output file: " + outputFile);
    }
}