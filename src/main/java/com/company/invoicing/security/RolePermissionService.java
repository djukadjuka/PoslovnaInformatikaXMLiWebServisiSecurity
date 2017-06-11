package com.company.invoicing.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by User on 6/1/2017.
 */
@Service
public class RolePermissionService {
    @Autowired
    private RolePermissionRepository repository;

    public void save(RolePermission rolepermission) {
        repository.saveAndFlush(rolepermission);
    }

    public RolePermission findOne(long id) {
        return repository.findOne(id);
    }

    public List<RolePermission> findAll() {
        return repository.findAll();
    }

    public void delete(long id) {
        repository.delete(id);
    }

    public void update(RolePermission rolepermission) {
        repository.saveAndFlush(rolepermission);
    }
}
