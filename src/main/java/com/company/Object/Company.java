package com.company.Object;

import org.apache.commons.lang3.BooleanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Company {
    private Integer id; // id CSV object
    private String name;
    private LocalDate foundationDate;
    private Integer capital;
    private String country;
    private Boolean isHeadQuarter;



    public Company(Integer id, String name, LocalDate foundationDate, Integer capital, String country, Boolean isHeadQuarter) {
        this.id = id;
        this.name = name;
        this.foundationDate = foundationDate;
        this.capital = capital;
        this.country = country;
        this.isHeadQuarter = isHeadQuarter;
    }

    public Company() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(LocalDate foundationDate) {
        this.foundationDate = foundationDate;
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getIsHeadQuarter() {
        return isHeadQuarter;
    }

    public void setIsHeadQuarter(Boolean isHeadQuarter) {
        this.isHeadQuarter = isHeadQuarter;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", FoundationDate='" + foundationDate + '\'' +
                ", Capital=" + capital +
                ", Country='" + country + '\'' +
                ", isHeadQuarter='" + isHeadQuarter + '\'' +
                '}';
    }
}
