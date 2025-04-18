package com.noder.restapi.dtos;

import jakarta.validation.constraints.NotNull;

public class ChargingStationCreateDTO {
    @NotNull
    private String location;
    @NotNull
    private String name;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
