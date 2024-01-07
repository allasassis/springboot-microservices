package com.allasassis.employeeservice.dto;

import com.allasassis.employeeservice.entity.Employee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "Employee Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;
    @Schema(description = "First Name")
    private String firstName;
    @Schema(description = "Last Name")
    private String lastName;
    private String email;
    @Schema(description = "Department Code")
    private String departmentCode;
    @Schema(description = "Organization Code")
    private String organizationCode;

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.departmentCode = employee.getDepartmentCode();
        this.organizationCode = employee.getOrganizationCode();
    }
}
