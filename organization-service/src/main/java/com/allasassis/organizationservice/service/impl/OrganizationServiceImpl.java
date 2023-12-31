package com.allasassis.organizationservice.service.impl;

import com.allasassis.organizationservice.dto.OrganizationDto;
import com.allasassis.organizationservice.entity.Organization;
import com.allasassis.organizationservice.repository.OrganizationRepository;
import com.allasassis.organizationservice.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository repository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto dto) {
        Organization org = new Organization(dto);
        repository.save(org);
        return new OrganizationDto(org);
    }

    @Override
    public OrganizationDto getOrgByCode(String code) {
        Organization org = repository.findByOrganizationCode(code);
        return new OrganizationDto(org);
    }
}
