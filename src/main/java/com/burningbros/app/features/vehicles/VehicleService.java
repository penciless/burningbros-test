package com.burningbros.app.features.vehicles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleQueryParser vehicleQueryParser;

    public Vehicle createVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        return vehicle;
    }

    public Optional<VehicleDTO> getVehicleById(Long vehicleId) {
        List<Map<String, Object>> results = vehicleRepository.getVehicleById(vehicleId);
        List<VehicleDTO> records = vehicleQueryParser.parseVehicles(results);
        return Optional.ofNullable(records.size() > 0 ? records.get(0) : null);
    }

    public List<VehicleDTO> getVehiclesByAdmin() {
        List<Map<String, Object>> results = vehicleRepository.getAllVehiclesByAdmin();
        List<VehicleDTO> records = vehicleQueryParser.parseVehicles(results);
        return records;
    }

    public List<VehicleDTO> getVehiclesByUser(Long userId) {
        List<Map<String, Object>> results = vehicleRepository.getAllVehiclesByUser(userId);
        List<VehicleDTO> records = vehicleQueryParser.parseVehicles(results);
        return records;
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicle) {
        if (vehicleRepository.existsById(id)) {
            vehicle.setId(id);
            return vehicleRepository.save(vehicle);
        }
        return null;
    }
}
