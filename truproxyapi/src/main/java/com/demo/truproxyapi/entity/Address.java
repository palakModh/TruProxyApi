package com.demo.truproxyapi.entity;

import com.demo.truproxyapi.constant.AppConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = AppConstants.ADDRESS_TABLE_NAME)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "postal_code", length = 100)
    private String postalCode;

    @Column(name = "country", length = 50)
    private String country;
}
