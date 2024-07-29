package com.burningbros.app.features.vehicles;

import java.util.List;

import com.burningbros.app.features.maintenanceRecords.MaintenanceRecordDTO;


public class VehicleDTO extends Vehicle {

    private List<MaintenanceRecordDTO> maintenanceRecords;

    public VehicleDTO(Long id, Long userId, String brand, String model, Integer year, String type) {
        this.id = id;
        this.userId = userId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.type = type;
    }

    public VehicleDTO(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.userId = vehicle.getUserId();
        this.brand = vehicle.getBrand();
        this.model = vehicle.getModel();
        this.year = vehicle.getYear();
        this.type = vehicle.getType();
    }

    public List<MaintenanceRecordDTO> getMaintenanceRecords() {
        return maintenanceRecords;
    }

    public void setMaintenanceRecords(List<MaintenanceRecordDTO> maintenanceRecords) {
        this.maintenanceRecords = maintenanceRecords;
    }
}