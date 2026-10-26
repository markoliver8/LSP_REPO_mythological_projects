
package org.howard.edu.lsp.midterm.inheritance;

/**
 * Represents a full-time employee who receives overtime pay.
 *
 * @author Student
 */
public class FullTimeEmployee extends Employee {

    /**
     * Creates a full-time employee.
     *
     * @param hoursWorked the number of hours worked
     * @param hourlyRate the hourly pay rate
     */
    public FullTimeEmployee(double hoursWorked, double hourlyRate) {
        super(hoursWorked, hourlyRate);
    }

    /**
     * Calculates regular pay and overtime pay.
     *
     * @return total pay, including overtime at 1.5 times the hourly rate
     */
    @Override
    public double calculatePay() {
        double hours = getHoursWorked();
        double rate = getHourlyRate();

        if (hours <= 40) {
            return hours * rate;
        }

        return (40 * rate) + ((hours - 40) * rate * 1.5);
    }
}
