package com.allasassis.employeeservice.service.impl;

import com.allasassis.employeeservice.dto.ApiDto;
import com.allasassis.employeeservice.dto.DepartmentDto;
import com.allasassis.employeeservice.dto.EmployeeDto;
import com.allasassis.employeeservice.entity.Employee;
import com.allasassis.employeeservice.exception.ResourceNotFoundException;
import com.allasassis.employeeservice.repository.EmployeeRepository;
import com.allasassis.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class EmployeeImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto dto) {
        try {
            restTemplate.getForEntity("http://localhost:8080/api/departments/" + dto.getDepartmentCode(), DepartmentDto.class);
        } catch (HttpClientErrorException ex) {
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

        ResponseEntity<DepartmentDto> depDto = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.get().getDepartmentCode(), DepartmentDto.class);
        return new ApiDto(new EmployeeDto(employee.get()), depDto.getBody());
    }
}
