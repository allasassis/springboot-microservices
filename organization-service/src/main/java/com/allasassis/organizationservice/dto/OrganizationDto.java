package com.allasassis.organizationservice.dto;

import com.allasassis.organizationservice.entity.Organization;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {

    private Long id;
    private String organizationName;
    private String organizationDescription;
    private String organizationCode;
    private LocalDateTime createdAt;

    public OrganizationDto(Organization org) {
        this.id = org.getId();
        this.organizationName = org.getOrganizationName();
        this.organizationCode = org.getOrganizationCode();
        this.organizationDescription = org.getOrganizationDescription();
    }
}
