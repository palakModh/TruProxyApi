package com.demo.truproxyapi.controller;

import com.demo.truproxyapi.response.CompanyOfficerDtlsResponse;
import com.demo.truproxyapi.service.CompanyDtlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/Companies/v1")
public class CompanyOfficerDtlsController {

    @Autowired
    CompanyDtlsService service;

    @GetMapping(value = "/search")
    public ResponseEntity<CompanyOfficerDtlsResponse> searchCompany(@RequestParam(name = "Query", required = false) String query, @RequestParam(required = false) String isActive)
    {
        CompanyOfficerDtlsResponse resp = service.getCompanyDtls(query, isActive);
        if(!ObjectUtils.isEmpty(resp))
        {
            return ResponseEntity.ok(resp);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(CompanyOfficerDtlsResponse.builder().status(HttpStatus.NOT_FOUND.value()).message("Not found").build());
        }
    }

    @GetMapping(value = "/officers")
    public ResponseEntity<CompanyOfficerDtlsResponse> getCompanyOfficers(@RequestParam(name = "company_name", required = false) String name,
                                                            @RequestParam(name = "company_number", required = false) String number)
    {
        CompanyOfficerDtlsResponse resp = service.getCompanyOfficers(name, number);
        if(!ObjectUtils.isEmpty(resp))
        {
            return ResponseEntity.ok(resp);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(CompanyOfficerDtlsResponse.builder().status(HttpStatus.NOT_FOUND.value()).message("Not found").build());
        }
    }

}
