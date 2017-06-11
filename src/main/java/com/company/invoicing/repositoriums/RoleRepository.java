package com.company.invoicing.repositoriums;

import com.company.invoicing.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by User on 6/1/2017.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
