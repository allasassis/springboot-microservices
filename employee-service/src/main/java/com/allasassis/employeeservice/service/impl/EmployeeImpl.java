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
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public ApiDto getEmployeeById(Long id) throws ResourceNotFoundException{
        Optional<Employee> employee = repository.findById(id);
        if (employee.isEmpty()) {
            throw new ResourceNotFoundException("The user whose ID is " + id + ", does not exist!");
        }

        DepartmentDto depDto = apiClient.getDepartmentByCode(employee.get().getDepartmentCode());
        return new ApiDto(new EmployeeDto(employee.get()), depDto);
    }

    public ApiDto getDefaultDepartment(Long id, Exception e) throws ResourceNotFoundException{
        Optional<Employee> employee = repository.findById(id);
        if (employee.isEmpty()) {
            throw new ResourceNotFoundException("The user whose ID is " + id + ", does not exist!");
        }

        DepartmentDto depDto = new DepartmentDto();
        depDto.setDepartmentDescription("Default Department");
        depDto.setDepartmentName("Default Department");
        depDto.setDepartmentCode("000000");
        return new ApiDto(new EmployeeDto(employee.get()), depDto);
    }
}
