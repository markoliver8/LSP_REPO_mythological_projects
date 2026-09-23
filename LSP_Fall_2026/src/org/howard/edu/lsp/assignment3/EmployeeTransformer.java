package org.howard.edu.lsp.assignment3;

import java.util.List;

public class EmployeeTransformer {

    public void transform(List<Employee> employees) {

        for (Employee employee : employees) {

            employee.calculateGrossPay();
            employee.determinePayLevel();
            employee.determineEmploymentStatus();
        }
    }
}