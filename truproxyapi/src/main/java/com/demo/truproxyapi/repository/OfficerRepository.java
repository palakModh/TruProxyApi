package com.demo.truproxyapi.repository;

import com.demo.truproxyapi.entity.CompanyDtls;
import com.demo.truproxyapi.entity.OfficerDtls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficerRepository extends JpaRepository<OfficerDtls, Long> {
}