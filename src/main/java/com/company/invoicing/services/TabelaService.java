package com.company.invoicing.services;

import com.company.invoicing.models.Tabela;
import com.company.invoicing.repositoriums.TabelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by User on 6/14/2017.
 */
@Service
public class TabelaService {

    @Autowired
    private TabelaRepository repository;

    public void save(Tabela tabela) {
        repository.saveAndFlush(tabela);
    }

    public Tabela findOne(long id) {
        return repository.findOne(id);
    }

    public List<Tabela> findAll() {
        return repository.findAll();
    }

    public void delete(long id) {
        repository.delete(id);
    }

    public void update(Tabela tabela) {
        repository.saveAndFlush(tabela);
    }
}
