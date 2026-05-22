package com.tekup.skillmatchai.repository;

import com.tekup.skillmatchai.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
