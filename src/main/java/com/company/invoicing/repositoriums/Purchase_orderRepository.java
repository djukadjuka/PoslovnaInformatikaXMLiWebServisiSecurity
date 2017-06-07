package com.company.invoicing.repositoriums;

import com.company.invoicing.models.Purchase_order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Purchase_orderRepository extends JpaRepository<Purchase_order,Long> {

}