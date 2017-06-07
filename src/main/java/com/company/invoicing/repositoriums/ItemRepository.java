package com.company.invoicing.repositoriums;

import com.company.invoicing.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {

}