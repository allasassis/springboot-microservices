package com.allasassis.employeeservice.service.impl;

import com.allasassis.employeeservice.dto.ApiDto;
import com.allasassis.employeeservice.dto.DepartmentDto;
import com.allasassis.employeeservice.dto.EmployeeDto;
import com.allasassis.employeeservice.entity.Employee;
import com.allasassis.employeeservice.exception.ResourceNotFoundException;
import com.allasassis.employeeservice.repository.EmployeeRepository;
import com.allasassis.employeeservice.service.APIClient;
import com.allasassis.employeeservice.service.EmployeeService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto dto) {
        try {
            apiClient.getDepartmentByCode(dto.getDepartmentCode());
        } catch (FeignException ex) {
            throw new ResourceNotFoundException("The department whose code is " + dto.getDepartmentCode() + ", does not exist!");
        }
        Employee employee = new Employee(dto);
        repository.save(employee);
        return new EmployeeDto(employee);
    }

    @Override
    public ApiDto getEmployeeById(Long id) throws ResourceNotFoundException{
        Optional<Employee> employee = repository.findById(id);
        if (employee.isEmpty()) {
            throw new ResourceNotFoundException("The user whose ID is " + id + ", does not exist!");
        }

        DepartmentDto depDto = apiClient.getDepartmentByCode(employee.get().getDepartmentCode());
        return new ApiDto(new EmployeeDto(employee.get()), depDto);
    }
}
