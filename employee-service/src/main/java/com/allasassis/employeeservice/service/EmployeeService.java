package com.allasassis.employeeservice.service;

import com.allasassis.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto dto);

    EmployeeDto getEmployeeById(Long id);
}
