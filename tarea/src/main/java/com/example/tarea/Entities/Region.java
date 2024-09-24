package com.example.tarea.Entities;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @Column(name = "region_id", nullable = false)
    private BigDecimal regionId;

    @Column(name = "region_name", length = 25)
    private String regionName;

    public BigDecimal getRegionId() {
        return regionId;
    }

    public void setRegionId(BigDecimal regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    // Getters and Setters
}
