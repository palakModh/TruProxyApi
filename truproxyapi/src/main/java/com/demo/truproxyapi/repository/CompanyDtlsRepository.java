package com.demo.truproxyapi.repository;

import com.demo.truproxyapi.entity.CompanyDtls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyDtlsRepository extends JpaRepository<CompanyDtls, String> {

    List<CompanyDtls> findByCompanyNumber(String number);

    List<CompanyDtls> findByCompanyNumberAndStatus(String number, String status);

    List<CompanyDtls> findByCompanyNameAndStatus(String name, String status);

    List<CompanyDtls> findByCompanyName(String companyName);

    List<CompanyDtls> findByStatus(String status);
}