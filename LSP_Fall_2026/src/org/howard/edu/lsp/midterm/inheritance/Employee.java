
package org.howard.edu.lsp.midterm.inheritance;

/**
 * Represents an employee with hours worked and an hourly rate.
 *
 * @author Student
 */
public abstract class Employee {
    private final double hoursWorked;
    private final double hourlyRate;

    /**
     * Creates an employee.
     *
     * @param hoursWorked the number of hours worked
     * @param hourlyRate the hourly pay rate
     */
    public Employee(double hoursWorked, double hourlyRate) {
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    /**
     * Returns the number of hours worked.
     *
     * @return hours worked
     */
    public double getHoursWorked() {
        return hoursWorked;
    }

    /**
     * Returns the employee's hourly rate.
     *
     * @return hourly rate
     */
    public double getHourlyRate() {
        return hourlyRate;
    }

    /**
     * Calculates pay according to the employee's type.
     *
     * @return calculated pay
     */
    public abstract double calculatePay();
}
