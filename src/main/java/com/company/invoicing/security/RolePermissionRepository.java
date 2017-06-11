package com.company.invoicing.security;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by User on 6/1/2017.
 */
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
}
