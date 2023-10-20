package com.demo.truproxyapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OfficerDtlsDTO {
    private Long officerId;
    private String name;
    private String role;
    private LocalDateTime appointedOn;
    private AddressDTO address;
}
