package com.example.countries.models;

import javax.persistence.*;

@Entity
@Table(name="countries") // This is where we set up the name of the table to be referenced when talking about our db
public class Country {
    @Id //  This is where we are telling spring that this table has a primary id field
    @GeneratedValue(strategy = GenerationType.AUTO) //  This AUTO will be changed out at some point. Apparently there is a better way.
    private long countryid;
    private String name;
    private long population;
    private long landmasskm2;
    private long medianage;

    public Country() {
    }

    public Country(long population, String name, long landmasskm2, long medianage) {
        this.population = population;
        this.name = name;
        this.landmasskm2 = landmasskm2;
        this.medianage = medianage;
    }

    public long getCountryid() {
        return countryid;
    }

    public void setCountryid(long countryid) {
        this.countryid = countryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public long getLandmasskm2() {
        return landmasskm2;
    }

    public void setLandmasskm2(long landmasskm2) {
        this.landmasskm2 = landmasskm2;
    }

    public long getMedianage() {
        return medianage;
    }

    public void setMedianage(long medianage) {
        this.medianage = medianage;
    }
}
