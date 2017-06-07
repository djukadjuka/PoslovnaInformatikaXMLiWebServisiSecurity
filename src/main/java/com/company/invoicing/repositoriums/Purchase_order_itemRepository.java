package com.company.invoicing.repositoriums;

import com.company.invoicing.models.Purchase_order_item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Purchase_order_itemRepository extends JpaRepository<Purchase_order_item,Long> {

}