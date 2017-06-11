package com.company.invoicing.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by User on 6/1/2017.
 */
@Service
public class PermissionService {
    @Autowired
    private PermissionRepository repository;

    public void save(Permission permission) {
        repository.saveAndFlush(permission);
    }

    public Permission findOne(long id) {
        return repository.findOne(id);
    }

    public List<Permission> findAll() {
        return repository.findAll();
    }

    public void delete(long id) {
        repository.delete(id);
    }

    public void update(Permission permission) {
        repository.saveAndFlush(permission);
    }
}
