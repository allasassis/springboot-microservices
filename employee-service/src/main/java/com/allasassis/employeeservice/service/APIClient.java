package com.allasassis.employeeservice.service;

import com.allasassis.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {

    @GetMapping("/api/departments/{departmentcode}")
    DepartmentDto getDepartmentByCode(@PathVariable(value = "departmentcode") String departmentCode);
}
