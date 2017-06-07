package com.company.invoicing.repositoriums;

import com.company.invoicing.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency,Long> {

}