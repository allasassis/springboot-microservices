package com.allasassis.organizationservice.service;

import com.allasassis.organizationservice.dto.OrganizationDto;

public interface OrganizationService {

    OrganizationDto saveOrganization(OrganizationDto dto);

    OrganizationDto getOrgByCode(String code);
}
