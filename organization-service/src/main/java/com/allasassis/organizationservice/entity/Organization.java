package com.allasassis.organizationservice.entity;

import com.allasassis.organizationservice.dto.OrganizationDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String organizationName;
    private String organizationDescription;
    @Column(nullable = false)
    private String organizationCode;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Organization(OrganizationDto dto) {
        this.organizationCode = dto.getOrganizationCode();
        this.organizationName = dto.getOrganizationName();
        this.organizationDescription = dto.getOrganizationDescription();
    }
}
