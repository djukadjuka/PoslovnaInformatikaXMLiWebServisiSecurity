package com.company.invoicing.services;

import com.company.invoicing.models.Units_of_measurement;
import com.company.invoicing.repositoriums.Units_of_measurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Units_of_measurementService {

    @Autowired
    private Units_of_measurementRepository repository;

    public List<Units_of_measurement> findAll(){
        return repository.findAll();
    }

    public Units_of_measurement findOne(long id){
        return repository.findOne(id);
    }

    public void create(Units_of_measurement units_of_measurement){
        repository.save(units_of_measurement);
    }

    public void update(Units_of_measurement units_of_measurement){
        repository.save(units_of_measurement);
    }

    public void remove(Long id){
        repository.delete(id);
    }

    public List<Units_of_measurement> search(Units_of_measurement units_of_measurement){
        List<Units_of_measurement> units_of_measurements=new ArrayList<>();
        for(Units_of_measurement uom : repository.findAll()){
            if(units_of_measurement.getName()==null || units_of_measurement.getName().equals("") || uom.getName().toLowerCase().contains(units_of_measurement.getName().toLowerCase())){
                if(units_of_measurement.getAbbreviation()==null || units_of_measurement.getAbbreviation().equals("") || uom.getAbbreviation().toLowerCase().contains(units_of_measurement.getAbbreviation().toLowerCase())){
                    units_of_measurements.add(uom);
                }
            }
        }
        return units_of_measurements;
    }

}
