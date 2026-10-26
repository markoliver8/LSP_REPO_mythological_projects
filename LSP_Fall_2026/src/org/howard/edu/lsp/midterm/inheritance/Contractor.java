
package org.howard.edu.lsp.midterm.inheritance;

/**
 * Represents a contractor receiving a 15 percent pay premium.
 *
 * @author Student
 */
public class Contractor extends Employee {

    /**
     * Creates a contractor.
     *
     * @param hoursWorked the number of hours worked
     * @param hourlyRate the hourly pay rate
     */
    public Contractor(double hoursWorked, double hourlyRate) {
        super(hoursWorked, hourlyRate);
    }

    /**
     * Calculates contractor pay with a 15 percent premium.
     *
     * @return total pay including the premium
     */
    @Override
    public double calculatePay() {
        return getHoursWorked() * getHourlyRate() * 1.15;
    }
}
