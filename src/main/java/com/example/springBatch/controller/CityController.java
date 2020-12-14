package com.example.springBatch.controller;

import java.util.List;

import com.example.springBatch.model.City;
import com.example.springBatch.model.ErrorMessage;
import com.example.springBatch.repository.CityRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/cities")
    public ResponseEntity<?> getAllCities() 
    {
        List<City> cities = cityRepository.findAll();
        if(cities != null && !cities.isEmpty()) {
            logger.info(cities.toString());
            return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
        }
        else{
            logger.error("Resource not found that is City is empty");
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("resource not found"),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<?> getCityById(@PathVariable("id") String id) {
        City city = cityRepository.findById(Integer.parseInt(id)).get();
        logger.info(city.toString());
        return new ResponseEntity<City>(city, HttpStatus.OK);    
    }

    @PostMapping("/cities")
    public ResponseEntity<?> addNewCity(@RequestBody @NonNull City city){
        cityRepository.save(city);
        logger.info("New city added to the Database:"+ city.toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/cities/{id}")
    public ResponseEntity<?> removeCity(@PathVariable("id") String id){
        cityRepository.deleteById(Integer.parseInt(id));
        logger.info(id + "Entry Deleted");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/cities")
    public ResponseEntity<?> modifyCity(@RequestBody @NonNull City city){
        cityRepository.save(city);
        logger.info(city.toString() + "modification done");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/truncate")
    public ResponseEntity<?> truncateCity() {
        cityRepository.deleteAll();
        logger.info("Deleted all the entries");
        return new ResponseEntity<String>("Deleted all the entries", HttpStatus.OK);
    }
}
