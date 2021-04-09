package com.example.countries.controllers;

import com.example.countries.models.Country;
import com.example.countries.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {
    @Autowired
    CountryRepository countryrepository;

    @GetMapping(value = "/names/all", produces = {"application/json"})
    public ResponseEntity<?> getAll() {
        List<Country> countryList = new ArrayList<>();
        countryrepository.findAll().iterator().forEachRemaining(countryList::add);

        countryList.sort( (c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()) );

        return new ResponseEntity<>(countryList, HttpStatus.OK);
    }

    @GetMapping(value = "/names/start/{letter}", produces = {"application/json"})
    public ResponseEntity<?> findByFirstChar(@PathVariable char letter) {
        List<Country> countryList = new ArrayList<>();
        countryrepository.findAll().iterator().forEachRemaining(countryList::add);

        List<Country> foundCountries = new ArrayList<>();

        for (Country c : countryList) {
            if(c.getName().toUpperCase().charAt(0) == Character.toUpperCase(letter)) {
                foundCountries.add(c);
            }
        }

        foundCountries.sort( (c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));

        return new ResponseEntity<>(foundCountries, HttpStatus.OK);
    }

    @GetMapping(value = "/population/total", produces = {"application/json"})
    public ResponseEntity<?> findTotalPopulation() {
        List<Country> countryList = new ArrayList<>();
        countryrepository.findAll().iterator().forEachRemaining(countryList::add);

        long totalPop = 0;

        for(Country c : countryList) {
            totalPop += c.getPopulation();
        }

        System.out.printf("Total Population is %d", totalPop);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/population/max", produces = {"application/json"})
    public ResponseEntity<?> findMaxPop() {
        List<Country> countryList = new ArrayList<>();
        countryrepository.findAll().iterator().forEachRemaining(countryList::add);

        countryList.sort( (c1, c2) -> (int)(c2.getPopulation() - c1.getPopulation()));

        Country maxCountry = countryList.get(0);

        return new ResponseEntity<>(maxCountry, HttpStatus.OK);
    }

    @GetMapping(value = "/population/min", produces = {"application/json"})
    public ResponseEntity<?> findMinPop() {
        List<Country> countryList = new ArrayList<>();
        countryrepository.findAll().iterator().forEachRemaining(countryList::add);

        countryList.sort( (c1, c2) -> (int)(c1.getPopulation() - c2.getPopulation()));

        Country maxCountry = countryList.get(0);

        return new ResponseEntity<>(maxCountry, HttpStatus.OK);
    }
    // Stretch
    @GetMapping(value = "/population/median", produces = {"application/json"})
    public ResponseEntity<?> findMedianPop() {
        List<Country> countryList = new ArrayList<>();
        countryrepository.findAll().iterator().forEachRemaining(countryList::add);

        countryList.sort( (c1, c2) -> (int)(c1.getPopulation() - c2.getPopulation()));

        Country maxCountry = countryList.get((countryList.size() / 2 ) + 1 );

        return new ResponseEntity<>(maxCountry, HttpStatus.OK);
    }
}

