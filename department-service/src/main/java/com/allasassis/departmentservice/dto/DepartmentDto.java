package com.allasassis.departmentservice.dto;

import com.allasassis.departmentservice.entity.Department;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
         description = "DepartmentDto Model Information"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private Long id;
    @Schema(description = "Department Name")
    private String departmentName;
    @Schema(description = "Department Description")
    private String departmentDescription;
    @Schema(description = "Department Code")
    private String departmentCode;

    public DepartmentDto(Department department) {
        this.id = department.getId();
        this.departmentName = department.getDepartmentName();
        this.departmentDescription = department.getDepartmentDescription();
        this.departmentCode = department.getDepartmentCode();
    }
}