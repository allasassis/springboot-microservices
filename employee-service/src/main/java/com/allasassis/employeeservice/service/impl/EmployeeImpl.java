package com.allasassis.employeeservice.service.impl;

import com.allasassis.employeeservice.dto.ApiDto;
import com.allasassis.employeeservice.dto.DepartmentDto;
import com.allasassis.employeeservice.dto.EmployeeDto;
import com.allasassis.employeeservice.dto.OrganizationDto;
import com.allasassis.employeeservice.entity.Employee;
import com.allasassis.employeeservice.exception.ResourceNotFoundException;
import com.allasassis.employeeservice.repository.EmployeeRepository;
import com.allasassis.employeeservice.service.APIClient;
import com.allasassis.employeeservice.service.APIClientOrg;
import com.allasassis.employeeservice.service.EmployeeService;
import feign.FeignException;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private APIClient apiClient;

    @Autowired
    private APIClientOrg apiClientOrg;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto dto) {
        try {
            apiClient.getDepartmentByCode(dto.getDepartmentCode());
        } catch (FeignException ex) {
            throw new ResourceNotFoundException("The DEPARTMENT whose code is " + dto.getDepartmentCode() + ", does not exist!");
        }

        try {
            apiClientOrg.getOrgByCode(dto.getOrganizationCode());
        } catch (FeignException ex) {
            throw new ResourceNotFoundException("The ORGANIZATION whose code is " + dto.getOrganizationCode() + ", does not exist!");
        }

        Employee employee = new Employee(dto);
        repository.save(employee);
        return new EmployeeDto(employee);
    }

//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public ApiDto getEmployeeById(Long id) throws ResourceNotFoundException{
        Employee employee = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The user whose ID is " + id + ", does not exist!"));
        DepartmentDto depDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());
        OrganizationDto orgDto = apiClientOrg.getOrgByCode(employee.getOrganizationCode());
        return new ApiDto(new EmployeeDto(employee), depDto, orgDto);
    }

    public ApiDto getDefaultDepartment(Long id, Exception e) throws ResourceNotFoundException{
        Employee employee = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The user whose ID is " + id + ", does not exist!"));
        return new ApiDto(new EmployeeDto(employee), new DepartmentDto("Default Department", "Default Department", "000000"),
                new OrganizationDto());
    }
}
