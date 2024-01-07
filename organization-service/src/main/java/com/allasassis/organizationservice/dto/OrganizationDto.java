package com.allasassis.organizationservice.dto;

import com.allasassis.organizationservice.entity.Organization;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(
        description = "OrganizationDto Model Information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {

    private Long id;
    @Schema(description = "Organization Name")
    private String organizationName;
    @Schema(description = "Organization Description")
    private String organizationDescription;
    @Schema(description = "Organization Code")
    private String organizationCode;
    @Schema(description = "When it was created")
    private LocalDateTime createdAt;

    public OrganizationDto(Organization org) {
        this.id = org.getId();
        this.organizationName = org.getOrganizationName();
        this.organizationCode = org.getOrganizationCode();
        this.organizationDescription = org.getOrganizationDescription();
    }
}
