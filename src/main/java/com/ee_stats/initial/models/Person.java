package com.ee_stats.initial.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "country")
    private String country;

    @Column(name = "target")
    private Boolean target;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "incorporation_date")
    private LocalDate incorporationDate;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public Boolean getTarget() {
        return target;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getIncorporationDate() {
        return incorporationDate;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

}
