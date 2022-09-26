package com.solvd.underground.domain.employee;

import com.solvd.underground.domain.exception.UnsupportedOperationException;

public class EmploymentFactory {

    public static Employee employ(EmployeeType type) {
        Employee employee = null;
        switch(type){
            case DRIVER:
                employee = new Driver();
                break;
            case CASHIER:
                employee = new Cashier();
                break;
            default:
                throw new UnsupportedOperationException("This operation is not supported for the employee.");
        }
        return employee;
    }
}