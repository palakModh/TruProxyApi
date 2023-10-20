package com.demo.truproxyapi.entity;

import com.demo.truproxyapi.constant.AppConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = AppConstants.COMPANY_TABLE_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDtls {
    @Id
    @Column(name = "company_number")
    private String companyNumber;

    @Column(name = "company_name", length = 100)
    private String companyName;

    @Column(name = "company_status", length = 20)
    private String status;

    @Column(name = "date_of_creation")
    private LocalDate dateOfCreation;

    @BatchSize(size = 10)
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OfficerDtls> officers;

    @OneToOne
    @JoinColumn(name = "address_id", insertable = false, updatable = false)
    private Address address;

    @Column(name = "address_id")
    private Long address_id;

    @Embedded
    private DeafultFields deafultFields;
}
