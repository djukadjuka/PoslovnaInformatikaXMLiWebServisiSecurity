package com.company.invoicing.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by User on 6/1/2017.
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public void save(Role role) {
        repository.saveAndFlush(role);
    }

    public Role findOne(long id) {
        return repository.findOne(id);
    }

    public List<Role> findAll() {
        return repository.findAll();
    }

    public void delete(long role_id) {
        repository.delete(role_id);
    }

    public void update(Role role) {
        repository.saveAndFlush(role);
    }
}
