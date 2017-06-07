package com.company.invoicing.repositoriums;

import com.company.invoicing.models.Invoice_item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Invoice_itemRepository extends JpaRepository<Invoice_item,Long> {

}