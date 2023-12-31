package com.allasassis.organizationservice.controller;

import com.allasassis.organizationservice.dto.OrganizationDto;
import com.allasassis.organizationservice.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrg(@RequestBody OrganizationDto dto) {
        return new ResponseEntity<>(organizationService.saveOrganization(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{code}")
    public ResponseEntity<OrganizationDto> getOrgByCode(@PathVariable String code) {
        return new ResponseEntity<>(organizationService.getOrgByCode(code), HttpStatus.OK);
    }
}
