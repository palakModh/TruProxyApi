package com.demo.truproxyapi.repository;

import com.demo.truproxyapi.entity.Address;
import com.demo.truproxyapi.entity.CompanyDtls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {}