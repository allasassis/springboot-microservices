package com.allasassis.departmentservice.service.impl;

import com.allasassis.departmentservice.dto.DepartmentDto;
import com.allasassis.departmentservice.entity.Department;
import com.allasassis.departmentservice.exception.ResourceNotFoundException;
import com.allasassis.departmentservice.repository.DepartmentRepository;
import com.allasassis.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository repository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto dto) {
        Department department = new Department(dto);
        repository.save(department);
        return new DepartmentDto(department);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department dep = repository.findByDepartmentCode(code);
        if (dep == null) {
            throw new ResourceNotFoundException("The department whose code is " + code + ", does not exist!");
        }
        return new DepartmentDto(dep);
    }
}
