package com.example.springBatch.repository;

import com.example.springBatch.model.City;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
    
}
