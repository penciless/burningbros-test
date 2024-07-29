package com.burningbros.app.features.maintenanceRecords;

import java.util.Date;

import com.burningbros.app.features.vehicles.VehicleDTO;


public class MaintenanceRecordDTO extends MaintenanceRecord {
    private VehicleDTO vehicle;
    
    public MaintenanceRecordDTO(Long id, Long vehicleId, Date date, String description, Double cost) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.date = date;
        this.description = description;
        this.cost = cost;
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }
}
