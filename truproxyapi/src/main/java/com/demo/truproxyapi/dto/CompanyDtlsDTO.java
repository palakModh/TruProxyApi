package com.demo.truproxyapi.dto;

import com.demo.truproxyapi.entity.OfficerDtls;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Data
@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDtlsDTO {
    private Long companyNumber;
    private String companyName;
    private String status;
    private LocalDate dateOfCreation;
    private List<OfficerDtlsDTO> officers;
    private AddressDTO address;
}
