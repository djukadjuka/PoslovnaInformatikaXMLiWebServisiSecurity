package com.company.invoicing.repositoriums;

import com.company.invoicing.models.Price_list_item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Price_list_itemRepository extends JpaRepository<Price_list_item,Long> {

}