package com.allasassis.departmentservice.controller;

import com.allasassis.departmentservice.dto.DepartmentDto;
import com.allasassis.departmentservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto dto) {
        return new ResponseEntity<>(departmentService.saveDepartment(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{departmentcode}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable(value = "departmentcode") String departmentCode) {
        return new ResponseEntity<>(departmentService.getDepartmentByCode(departmentCode), HttpStatus.OK);
    }
}
