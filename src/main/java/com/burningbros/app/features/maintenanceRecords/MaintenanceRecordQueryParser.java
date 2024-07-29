package com.burningbros.app.features.maintenanceRecords;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.burningbros.app.features.vehicles.VehicleDTO;

@Component
public class MaintenanceRecordQueryParser {
    public List<MaintenanceRecordDTO> parseMaintenanceRecords(List<Map<String, Object>> results) {
        Map<Long, VehicleDTO> vehicles = parseUniqueVehicles(results);

        Map<Long, MaintenanceRecordDTO> unique = new HashMap<>();
        List<MaintenanceRecordDTO> records = new ArrayList<>();

        results.forEach(result -> {
            Long id = ((BigInteger) result.get("ID")).longValue();

            if (unique.containsKey(id)) return;

            Long vehicleId = ((BigInteger) result.get("VEHICLEID")).longValue();
            Date date = (Date) result.get("DATE");
            String description = (String) result.get("DESCRIPTION");
            Double cost = (Double) result.get("COST");

            MaintenanceRecordDTO record = new MaintenanceRecordDTO(id, vehicleId, date, description, cost);

            record.setVehicle(vehicles.get(vehicleId));

            unique.put(id, record);

            records.add(record);
        });

        return records;
    }

    public Map<Long, VehicleDTO> parseUniqueVehicles(List<Map<String, Object>> results) {
        Map<Long, VehicleDTO> uniqueVehicleMap = new HashMap<>();
        
        results.forEach(result -> {
            Long vehicleId = ((BigInteger) result.get("VEHICLEID")).longValue();

            if (uniqueVehicleMap.containsKey(vehicleId)) return;

            Long userId = ((BigInteger) result.get("USERID")).longValue();
            String brand = (String) result.get("BRAND");
            String model = (String) result.get("MODEL");
            Integer year = (Integer) result.get("YEAR");
            String type = (String) result.get("TYPE");

            VehicleDTO newVehicle = new VehicleDTO(vehicleId, userId, brand, model, year, type);

            uniqueVehicleMap.put(vehicleId, newVehicle);
        });

        return uniqueVehicleMap;
    }
}
