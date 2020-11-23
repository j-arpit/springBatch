package com.example.springBatch.batch;

import java.util.List;

import com.example.springBatch.model.City;
import com.example.springBatch.repository.CityRepository;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBWriter implements ItemWriter<City> {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public void write(List<? extends City> items) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("Data loaded for City" + items);
        cityRepository.saveAll(items);

    }
    
}
