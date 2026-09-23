package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Employee {

    private int employeeId;
    private String name;
    private String department;
    private BigDecimal hoursWorked;
    private BigDecimal hourlyRate;

    private BigDecimal grossPay;
    private String payLevel;
    private String employmentStatus;

    public Employee(int employeeId, String name, String department,
                    BigDecimal hoursWorked, BigDecimal hourlyRate) {

        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public BigDecimal getHoursWorked() {
        return hoursWorked;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public BigDecimal getGrossPay() {
        return grossPay;
    }

    public String getPayLevel() {
        return payLevel;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void calculateGrossPay() {

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

        grossPay = regularPay.add(overtimePay);

        // IT bonus
        if (department.equals("IT")) {
            grossPay = grossPay.multiply(new BigDecimal("1.05"));
        }

        grossPay = grossPay.setScale(2, RoundingMode.HALF_UP);
    }

    public void determinePayLevel() {

        if (grossPay.compareTo(new BigDecimal("500")) < 0) {
            payLevel = "Low";
        } else if (grossPay.compareTo(new BigDecimal("1000")) < 0) {
            payLevel = "Standard";
        } else if (grossPay.compareTo(new BigDecimal("2000")) < 0) {
            payLevel = "High";
        } else {
            payLevel = "Executive";
        }
    }

    public void determineEmploymentStatus() {

        if (hoursWorked.compareTo(new BigDecimal("30")) < 0) {
            employmentStatus = "Part-Time";
        } else {
            employmentStatus = "Full-Time";
        }
    }

    public String toCSV() {

        BigDecimal outputHours =
                hoursWorked.setScale(2, RoundingMode.HALF_UP);

        BigDecimal outputRate =
                hourlyRate.setScale(2, RoundingMode.HALF_UP);

        return employeeId + ","
                + name + ","
                + department + ","
                + outputHours.toPlainString() + ","
                + outputRate.toPlainString() + ","
                + grossPay.toPlainString() + ","
                + payLevel + ","
                + employmentStatus;
    }
}