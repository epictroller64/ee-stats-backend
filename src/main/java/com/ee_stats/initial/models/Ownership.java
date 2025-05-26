package com.ee_stats.initial.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ownerships")
public class Ownership {
    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Person ownerId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "role")
    private String role;

    @Column(name = "asset_id")
    private String assetId;

    @Column(name = "registry_code")
    private String registryCode;

    @Column(name = "target")
    private Boolean target;

    public Ownership() {
    }

    public String getId() {
        return id;
    }

    public Person getOwnerId() {
        return ownerId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getRole() {
        return role;
    }

    public String getAssetId() {
        return assetId;
    }

    public String getRegistryCode() {
        return registryCode;
    }

    public Boolean getTarget() {
        return target;
    }
}