package com.allasassis.employeeservice.service;

import com.allasassis.employeeservice.dto.ApiDto;
import com.allasassis.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto dto);

    ApiDto getEmployeeById(Long id);
}
