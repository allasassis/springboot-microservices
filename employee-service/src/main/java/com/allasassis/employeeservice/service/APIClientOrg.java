package com.allasassis.employeeservice.service;

import com.allasassis.employeeservice.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface APIClientOrg {

    @GetMapping("/api/organization/{code}")
    OrganizationDto getOrgByCode(@PathVariable(value = "code") String orgCode);
}
