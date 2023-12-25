package com.allasassis.departmentservice.entity;

import com.allasassis.departmentservice.dto.DepartmentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;

    public Department(DepartmentDto dto) {
        this.departmentName = dto.getDepartmentName();
        this.departmentDescription = dto.getDepartmentDescription();
        this.departmentCode = dto.getDepartmentCode();
    }
}
