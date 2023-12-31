package com.allasassis.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private Long id;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;

    public DepartmentDto(String departmentName, String departmentDescription, String departmentCode) {
        this.departmentCode = departmentCode;
        this.departmentDescription = departmentDescription;
        this.departmentName = departmentName;
    }
}
