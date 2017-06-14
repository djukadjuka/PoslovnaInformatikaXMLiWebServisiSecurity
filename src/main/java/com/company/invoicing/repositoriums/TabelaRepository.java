package com.company.invoicing.repositoriums;

import com.company.invoicing.models.Tabela;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by User on 6/14/2017.
 */
public interface TabelaRepository extends JpaRepository<Tabela, Long> {
}
