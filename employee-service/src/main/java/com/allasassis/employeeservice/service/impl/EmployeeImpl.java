package com.allasassis.employeeservice.service.impl;

import com.allasassis.employeeservice.dto.EmployeeDto;
import com.allasassis.employeeservice.entity.Employee;
import com.allasassis.employeeservice.exception.ResourceNotFoundException;
import com.allasassis.employeeservice.repository.EmployeeRepository;
import com.allasassis.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto dto) {
        Employee employee = new Employee(dto);
        repository.save(employee);
        return new EmployeeDto(employee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) throws ResourceNotFoundException{
        Optional<Employee> employee = repository.findById(id);
        if (employee.isEmpty()) {
            throw new ResourceNotFoundException("The user whose ID is " + id + ", does not exist!");
        }
        return new EmployeeDto(employee.get());
    }
}
