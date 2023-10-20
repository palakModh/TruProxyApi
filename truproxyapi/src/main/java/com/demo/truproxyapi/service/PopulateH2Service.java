package com.demo.truproxyapi.service;

import com.demo.truproxyapi.entity.Address;
import com.demo.truproxyapi.entity.CompanyDtls;
import com.demo.truproxyapi.entity.DeafultFields;
import com.demo.truproxyapi.entity.OfficerDtls;
import com.demo.truproxyapi.repository.AddressRepository;
import com.demo.truproxyapi.repository.CompanyDtlsRepository;
import com.demo.truproxyapi.repository.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class PopulateH2Service {

    @Autowired
    CompanyDtlsRepository comRepo;

    @Autowired
    AddressRepository addressRepo;

    @Autowired
    OfficerRepository officerRepository;

    @PostConstruct
    public void populateDatabase() {

        Address address = Address.builder().postalCode("400086").country("UK").build();
        Address adrs = addressRepo.save(address);

        CompanyDtls company = CompanyDtls.builder().
                                companyNumber("123455").
                                companyName("ABC").
                                status("active").
                                dateOfCreation(LocalDate.of(1997, 10, 19)).
                                address_id(adrs.getAddressId()).
                                build();

        OfficerDtls offcr1 = OfficerDtls.builder().
                            name("BOXALL, Sarah Victoria").
                            role("Secretary").
                            company(company).
                            appointedOn(LocalDateTime.of(2021, 01, 05, 10, 00)).
                            address_id(adrs.getAddressId()).
                            deafultFields(new DeafultFields(LocalDateTime.now(), null)).
                            build();

        OfficerDtls offcr2 = OfficerDtls.builder().
                            name("Chris Nolan").
                            appointedOn(LocalDateTime.of(2021, 02, 05, 10, 00)).
                            role("Manager").
                            company(company).
                            resignedOn(LocalDateTime.now().minusDays(2)).
                            address_id(adrs.getAddressId()).
                            deafultFields(new DeafultFields(LocalDateTime.now(), null)).
                            build();

        List<OfficerDtls> officers = Arrays.asList(offcr1, offcr2);
        company.setOfficers(officers);

        company.setDeafultFields(new DeafultFields(LocalDateTime.now(), null));
        comRepo.save(company);

        CompanyDtls company2 = CompanyDtls.builder().
                companyNumber("123456").
                companyName("XYZ").
                status("inactive").
                dateOfCreation(LocalDate.of(2001, 10, 19)).
                address_id(adrs.getAddressId()).
                build();

        company2.setDeafultFields(new DeafultFields(LocalDateTime.now(), null));
        comRepo.save(company2);
    }
}
