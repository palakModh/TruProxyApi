package com.demo.truproxyapi.service;

import com.demo.truproxyapi.dto.CompanyDtlsDTO;
import com.demo.truproxyapi.entity.CompanyDtls;
import com.demo.truproxyapi.entity.OfficerDtls;
import com.demo.truproxyapi.repository.CompanyDtlsRepository;
import com.demo.truproxyapi.response.CompanyOfficerDtlsResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CompanyDtlsService {

    @Autowired
    CompanyDtlsRepository compRepo;

    public CompanyOfficerDtlsResponse getCompanyDtls(String searchTerm, String isActive) {
        List<CompanyDtls> compDtlList = null;
        List<CompanyDtlsDTO> companyDTOList = null;
        ModelMapper mapper = new ModelMapper();
        CompanyOfficerDtlsResponse compDtlsRes = null;

        if (!ObjectUtils.isEmpty(searchTerm)) {
            //if isActive param is passed, then fetch active companies
            if (!ObjectUtils.isEmpty(isActive) && isActive.equalsIgnoreCase("true")) {
                compDtlList = compRepo.findByCompanyNumberAndStatus(searchTerm, "active");
                if (CollectionUtils.isEmpty(compDtlList) && compDtlList.size() == 0) {
                    compDtlList = compRepo.findByCompanyNameAndStatus(searchTerm, "active");
                }
            } else {
                //else include all the companies
                compDtlList = compRepo.findByCompanyNumber(searchTerm);
                if (CollectionUtils.isEmpty(compDtlList) && compDtlList.size() == 0) {
                    compDtlList = compRepo.findByCompanyName(searchTerm);
                }
            }
        }
        else if(!ObjectUtils.isEmpty(isActive) && isActive.equalsIgnoreCase("true"))
        {
            //finding all the active companies
            compDtlList = compRepo.findByStatus("active");
        }
        else
        {
            //fetching all the companies in case of no query params
            compDtlList = compRepo.findAll();
        }

        if (!CollectionUtils.isEmpty(compDtlList) && compDtlList.size() > 0) {
            log.info("collection");
            companyDTOList = compDtlList.stream().map(comp -> {
                List<OfficerDtls> filteredOfficers = comp.getOfficers().stream()
                        .filter(officer -> officer.getResignedOn() == null)
                        .collect(Collectors.toList());
                comp.setOfficers(filteredOfficers);
                return comp;
            }).map(comp ->
                    mapper.map(comp, CompanyDtlsDTO.class)).collect(Collectors.toList());
            compDtlsRes = new CompanyOfficerDtlsResponse(HttpStatus.OK.value(), "SUCCESS", companyDTOList.size(), companyDTOList);
        }
        return compDtlsRes;
    }

    public CompanyOfficerDtlsResponse getCompanyOfficers(String name, String number) {
        List<CompanyDtls> companyDtlsList = null;
        List<CompanyDtlsDTO> companyDTOList = null;
        CompanyOfficerDtlsResponse companyOfficerResponse = null;
        ModelMapper mapper = new ModelMapper();


        if (!ObjectUtils.isEmpty(number)) {
            companyDtlsList = compRepo.findByCompanyNumber(number);
        }
        else if (!ObjectUtils.isEmpty(name)) {
            companyDtlsList = compRepo.findByCompanyName(name);
        }

        if (!CollectionUtils.isEmpty(companyDtlsList) && companyDtlsList.size() > 0) {
            companyDTOList = companyDtlsList.stream().map(comp -> {
                        List<OfficerDtls> filteredOfficers = comp.getOfficers().stream()
                                .filter(officer -> officer.getResignedOn() == null)
                                .collect(Collectors.toList());
                        comp.setOfficers(filteredOfficers);
                        return comp;
                    }).map(comp ->
                            mapper.map(comp, CompanyDtlsDTO.class)).collect(Collectors.toList());
            companyOfficerResponse = new CompanyOfficerDtlsResponse(HttpStatus.OK.value(), "SUCCESS", companyDTOList.size(), companyDTOList);
        }
        return companyOfficerResponse;
    }
}