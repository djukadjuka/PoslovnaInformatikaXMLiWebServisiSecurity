package com.company.invoicing.repositoriums;

import com.company.invoicing.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {

}