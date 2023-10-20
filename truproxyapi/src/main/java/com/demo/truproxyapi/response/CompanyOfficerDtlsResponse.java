package com.demo.truproxyapi.response;

import com.demo.truproxyapi.dto.CompanyDtlsDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyOfficerDtlsResponse {
    private int status;
    private String message;
    private int totalCompanies;
    private List<CompanyDtlsDTO> companies;
}
