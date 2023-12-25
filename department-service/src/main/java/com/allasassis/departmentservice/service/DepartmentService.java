package com.allasassis.departmentservice.service;

import com.allasassis.departmentservice.dto.DepartmentDto;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto dto);

    DepartmentDto getDepartmentByCode(String code);
}
