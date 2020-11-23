package com.example.springBatch.controller;

import java.util.List;

import com.example.springBatch.model.City;
import com.example.springBatch.model.ErrorMessage;
import com.example.springBatch.repository.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.lang.NonNull;

@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/cities")
    public ResponseEntity<?> getAllCities() {
        List<City> cities = cityRepository.findAll();
        if(cities != null && !cities.isEmpty()) {
            return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("resource not found"),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<?> getCityById(@PathVariable("id") String id) {
    
            return new ResponseEntity<City>(cityRepository.findById(Integer.parseInt(id)).get(), HttpStatus.OK);
        
    }

    @PostMapping("/cities")
    public ResponseEntity<?> addNewCity(@RequestBody @NonNull City city){
        cityRepository.save(city);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity<?> removeCity(@PathVariable("id") String id){
        cityRepository.deleteById(Integer.parseInt(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/cities")
    public ResponseEntity<?> modifyCity(@RequestBody @NonNull City city){
        cityRepository.save(city);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/truncate")
    public ResponseEntity<?> truncateCity() {
        cityRepository.deleteAll();
        System.out.println("Deleted all the entries");
        return new ResponseEntity<String>("Deleted all the entries", HttpStatus.OK);
    }
}
