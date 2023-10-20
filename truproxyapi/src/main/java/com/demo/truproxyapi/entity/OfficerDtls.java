package com.demo.truproxyapi.entity;

import com.demo.truproxyapi.constant.AppConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = AppConstants.OFFICER_TABLE_NAME)
@Data
public class OfficerDtls {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "officer_id")
    private Long officerId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "role", length = 100)
    private String role;

    @Column(name = "appointed_on", length = 20)
    private LocalDateTime appointedOn;

    @Column(name = "resigned_on")
    private LocalDateTime resignedOn;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyDtls company;

    @OneToOne
    @JoinColumn(name = "address_id", insertable = false, updatable = false)
    private Address address;

    @Column(name = "address_id")
    private Long address_id;

    @Embedded
    private DeafultFields deafultFields;
}
