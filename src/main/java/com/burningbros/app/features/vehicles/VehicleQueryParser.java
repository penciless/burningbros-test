package com.burningbros.app.features.vehicles;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.burningbros.app.features.maintenanceRecords.MaintenanceRecordDTO;

@Component
public class VehicleQueryParser {
    public Map<Long, List<MaintenanceRecordDTO>> parseMaintenanceRecords(List<Map<String, Object>> results) {
        Map<Long, List<MaintenanceRecordDTO>> map = new HashMap<>();

        results.forEach(result -> {
            Long vehicleId = ((BigInteger) result.get("VEHICLEID")).longValue();

            List<MaintenanceRecordDTO> records;

            if (map.containsKey(vehicleId)) {
                records = map.get(vehicleId);
            } else {
                records = new ArrayList<>();
                map.put(vehicleId, records);
            }

            Optional<BigInteger> recordIdBigInt = Optional.ofNullable((BigInteger) result.get("RECORDID"));
            if (recordIdBigInt.isEmpty()) return;

            Long recordId = recordIdBigInt.get().longValue();
            Date date = (Date) result.get("DATE");
            String description = (String) result.get("DESCRIPTION");
            Double cost = (Double) result.get("COST");

            MaintenanceRecordDTO record = new MaintenanceRecordDTO(recordId, vehicleId, date, description, cost);

            records.add(record);
        });

        return map;
    }

    public List<VehicleDTO> parseVehicles(List<Map<String, Object>> results) {
        Map<Long, List<MaintenanceRecordDTO>> mapListRecords = parseMaintenanceRecords(results);
        List<VehicleDTO> vehicles = new ArrayList<>();

        Map<Long, VehicleDTO> unique = new HashMap<>();
        
        results.forEach(result -> {
            Long vehicleId = ((BigInteger) result.get("VEHICLEID")).longValue();

            if (unique.containsKey(vehicleId)) return;

            Long userId = ((BigInteger) result.get("USERID")).longValue();
            String brand = (String) result.get("BRAND");
            String model = (String) result.get("MODEL");
            Integer year = (Integer) result.get("YEAR");
            String type = (String) result.get("TYPE");

            VehicleDTO vehicle = new VehicleDTO(vehicleId, userId, brand, model, year, type);

            if (mapListRecords.containsKey(vehicleId)) {
                vehicle.setMaintenanceRecords(mapListRecords.get(vehicleId));
            }

            unique.put(vehicleId, vehicle);

            vehicles.add(vehicle);
        });

        return vehicles;
    }
}
