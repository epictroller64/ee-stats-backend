package com.ee_stats.initial.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "directorships")
public class Directorship {
    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Person directorId;

    @Column(name = "asset")
    private String asset;

    @Column(name = "registry_code")
    private String registryCode;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "role")
    private String role;

    @Column(name = "target")
    private Boolean target;

    public Directorship() {

    }

    public String getId() {
        return id;
    }

    public Person getDirectorId() {
        return directorId;
    }

    public String getAsset() {
        return asset;
    }

    public String getRegistryCode() {
        return registryCode;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getRole() {
        return role;
    }

    public Boolean getTarget() {
        return target;
    }
}
