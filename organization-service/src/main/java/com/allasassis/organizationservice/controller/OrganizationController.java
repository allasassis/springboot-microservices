package com.allasassis.organizationservice.controller;

import com.allasassis.organizationservice.dto.OrganizationDto;
import com.allasassis.organizationservice.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Organization Service - Organization Controller",
        description = "Organization Controller Exposes REST APIs for Organization-Service"
)
@RestController
@RequestMapping("/api/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @Operation(
            summary = "Save Organization REST API",
            description = "Save Organization is used to save organization object in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrg(@RequestBody OrganizationDto dto) {
        return new ResponseEntity<>(organizationService.saveOrganization(dto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Organization By Code REST API",
            description = "Get Organization By Code is used to return an object from the database by the code"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{code}")
    public ResponseEntity<OrganizationDto> getOrgByCode(@PathVariable String code) {
        return new ResponseEntity<>(organizationService.getOrgByCode(code), HttpStatus.OK);
    }
}
