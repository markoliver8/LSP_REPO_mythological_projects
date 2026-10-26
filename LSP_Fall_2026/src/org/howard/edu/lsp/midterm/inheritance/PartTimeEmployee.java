
package org.howard.edu.lsp.midterm.inheritance;

/**
 * Represents a part-time employee paid at the standard hourly rate.
 *
 * @author Student
 */
public class PartTimeEmployee extends Employee {

    /**
     * Creates a part-time employee.
     *
     * @param hoursWorked the number of hours worked
     * @param hourlyRate the hourly pay rate
     */
    public PartTimeEmployee(double hoursWorked, double hourlyRate) {
        super(hoursWorked, hourlyRate);
    }

    /**
     * Calculates straight hourly pay.
     *
     * @return hours worked multiplied by the hourly rate
     */
    @Override
    public double calculatePay() {
        return getHoursWorked() * getHourlyRate();
    }
}
